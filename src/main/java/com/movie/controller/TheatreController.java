package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.movie.bean.BookingTable;
import com.movie.bean.Theatre;
import com.movie.repository.BookingTableRepo;
import com.movie.repository.MovieListRepository;
import com.movie.repository.TheatreRepository;
import com.movie.service.TheatreService;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/TheatreTable")
public class TheatreController {
 @Autowired
 private TheatreService theatreService;
 @Autowired
 private TheatreRepository theaRepo;
 @Autowired
 private MovieListRepository movS;
 @Autowired
 private BookingTableRepo bookRepo;
 @RequestMapping("/List")

 public String TheatreList(Model model, HttpSession session) {

  if (session.getAttribute("admin") == null) {

   return "redirect:/";

  }

  model.addAttribute("theaters", theatreService.TheatreList());

  System.out.println("List theater");

  // List<Theatre> theatreNames=;

  // model.addAttribute("theatreNames", theatreNames);
  try {
   return "theaterList";
  } catch (Exception e) {
   System.out.println(e);
  }
  return "theaterList";

 }

 @GetMapping("/TheatreTable/{id}")

 public Theatre findByTheatreId(@PathVariable("id") int id, HttpSession session) {

  if (session.getAttribute("admin") == null) {

   //return "redirect:/";

  }

  return theatreService.findByTheatreId(id);

 }

 @RequestMapping("/addTheater")

 public String addTheatre(Model m, HttpSession session) {

  if (session.getAttribute("admin") == null) {
   return "redirect:/";
  }

  m.addAttribute("theater", new Theatre());

  return "addTheater";

 }

 @RequestMapping("/saveTheater")

 public String saveTheater(@ModelAttribute("theater") Theatre theater, Model m, HttpSession session) {

  if (session.getAttribute("admin") == null) {

   return "redirect:/";

  }


  try {
   System.out.println("hello");
   String theaterName = theater.getThatreName().toLowerCase();
   theater.setThatreName(theaterName);
   theatreService.saveTheatre(theater);
   m.addAttribute("msg", "Inserted Successfully");
  } catch (DataIntegrityViolationException e) {
   // Handle the uniqueness constraint violation here
   m.addAttribute("errorMsg", "The theater name already exists. Please choose a different name.");

   // You can also add other error handling code as needed
   return "addTheater"; // Return the view with the error message
  }

  m.addAttribute("theaters", theatreService.TheatreList());
  return "redirect:/TheatreTable/List";

 }

 @GetMapping("/updateTheater/{id}")

 public String getupdateTheatre(@PathVariable("id") Integer theatreId, Model m, HttpSession session) {

  if (session.getAttribute("admin") == null) {

   return "redirect:/";

  }

  Theatre theater = theatreService.findByTheatreId(theatreId);

  m.addAttribute("theater", theater);

  //	System.out.println(theater.getThatreName());

  Theatre th = new Theatre();

  m.addAttribute("th", th);

  return "updateTheater";

 }

 @PostMapping("/updateTheater/{id}")

 public String updateTheater(@PathVariable("id") Long id,@ModelAttribute("th") Theatre theater, Model m, HttpSession session) {

  if (session.getAttribute("admin") == null) {

   return "redirect:/";

  }

  Theatre existingteater = theatreService.findByTheatreId(theater.getTheatreId());

  if (existingteater != null)

  {

   existingteater.setThatreName(theater.getThatreName());


   existingteater.setCity(theater.getCity());

   theatreService.saveTheatre(existingteater);

  }

  m.addAttribute("msg", "inserted success");

  m.addAttribute("theaters", theatreService.TheatreList());

  return "updateTheater";

 }


 @GetMapping("/deletTheater/{id}")

 public String deletTheater(@PathVariable("id") Integer Id, HttpSession session) {

  if (session.getAttribute("admin") == null) {

   return "redirect:/";

  }

  //movS.deleteAllById(theaRepo.findByTheatreId(Id).getTheatreId());

  theaRepo.deleteById((Id));

  System.out.println("delete");

  return "redirect:/TheatreTable/List";

 }

 @RequestMapping("/allBookings")

 public String allBookings(Model m, HttpSession session) {

  if (session.getAttribute("admin") == null) {

   return "redirect:/";

  }

  List < BookingTable > booki = bookRepo.findAll();

  //m.addAttribute("book", "Booked successfully");

  m.addAttribute("bookings", booki);

  return "allBookings";

 }

}