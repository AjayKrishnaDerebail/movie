package com.movie.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

import com.movie.bean.MovieList;
import com.movie.bean.Theatre;
import com.movie.repository.MovieListRepository;
import com.movie.repository.TheatreRepository;
import com.movie.service.MovieService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/MovieTable")
public class MovieController {
 @Autowired

 MovieService movieService;

 @Autowired

 MovieListRepository movieRepo;

 @Autowired

 TheatreRepository theaterRepo;

 @GetMapping("/movieList")

 public String MoviesList(Model model, HttpSession session) {

  if (session.getAttribute("admin") == null) {

   return "redirect:/";

  }

  model.addAttribute("movies", movieService.listMovies());
  return "moviesList";

 }

 @RequestMapping("/addMovie")

 public String addTheatre(Model m, HttpSession session) {
  if (session.getAttribute("admin") == null) {
   return "redirect:/";
  }

  m.addAttribute("theaters", theaterRepo.findAll());
  m.addAttribute("movie", new MovieList());
  return "addMovie";

 }

 @GetMapping("/addMovie")
 public String showAddMovieForm(Model model) {
  List<Theatre> theaters = theaterRepo.findAll();
  model.addAttribute("theaters", theaters);
  model.addAttribute("movie", new MovieList());
  return "addMovie";
 }

 @PostMapping("/addMovie")
 public String saveMovie(@ModelAttribute("movie") @Valid MovieList movie,@RequestParam("screen_id") int screenId,
                         Theatre theater, Model m, HttpSession session ) {
  if (session.getAttribute("admin") == null) {
   return "redirect:/";
  }

  System.out.println("saveMovie");

  movie.setTheater(movie.getTheater());
  movie.setScreenId(screenId);
  movie.setAvailabilityOfSeats(movie.getScreenCapacity());


  try {
   // Code to save the movie to the database
   movieService.saveMovie(movie);
   m.addAttribute("msg", "Inserted Succesfully");
   //return "redirect:/MovieTable/movieList";
  } catch (DataIntegrityViolationException e) {
   // If the exception is caught, it means the uniqueness constraint is violated
   m.addAttribute("errorMessage", "A movie with the same theater and screen ID already exists.");
   m.addAttribute("theaters", theaterRepo.findAll());
   m.addAttribute("movies", movieService.listMovies());
   return "addMovie";
  }

  m.addAttribute("theaters", theaterRepo.findAll());
  m.addAttribute("movies", movieService.listMovies());
  return "redirect:/MovieTable/movieList";
 }

 @PutMapping("/movies")
 public MovieList updateMovie(@RequestBody MovieList movie) {
  return movieService.saveMovie(movie);
 }

 @GetMapping("/updateMovie/{id}")
 public String updateMovie(@PathVariable("id") Integer id, Model model, HttpSession session) {
  if (session.getAttribute("admin") == null) {
   return "redirect:/";
  }

  MovieList movie = movieService.findByMovieId(id);
  List < Theatre > theaters = theaterRepo.findAll();
  model.addAttribute("theater", theaters);
  model.addAttribute("movie", movie);
  return "updateMovie";

 }

 @PostMapping("/updateMovie/{id}")

 public String updatedMovie(@ModelAttribute("movie") MovieList movie, Theatre theater, Model m, HttpSession session) {
  if (session.getAttribute("admin") == null) {
   return "redirect:/";
  }

  MovieList existingMovie = movieService.findByMovieId(movie.getMovieId());

  if (existingMovie != null) {

   existingMovie.setMovieName(movie.getMovieName());

   existingMovie.setGenre(movie.getGenre());

   existingMovie.setTheater(movie.getTheater());

   movieService.saveMovie(existingMovie);

  }

  List < Theatre > theaters = theaterRepo.findAll();

  m.addAttribute("theater", theaters);

  m.addAttribute("msg", "Updated Succesfully");

  m.addAttribute("movies", movieService.listMovies());

  return "updateMovie";

 }

 @GetMapping("/deletMovie/{id}")

 public String deletTheater(@PathVariable("id") Integer Id, HttpSession session) {

  if (session.getAttribute("admin") == null) {

   return "redirect:/";

  }

  System.out.println("delet");

  movieRepo.deleteById(Id);

  return "redirect:/MovieTable/movieList";

 }

}