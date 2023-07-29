package com.movie.controller;

import java.util.List;
import java.util.Random;

import com.movie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.bean.Admin;
import com.movie.bean.BookingTable;
import com.movie.bean.CustomerDetails;
import com.movie.bean.LoginCredentials;
import com.movie.bean.MovieList;
import com.movie.service.MovieService;
import com.movie.service.TheatreService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

 @Autowired
 CustomerDetailsRepository custRepo;

 @Autowired
 MovieListRepository movieRepo;
 @Autowired
 LoginCredentialsRepository logRepo;

 @Autowired
 MovieService moviSer;

 @Autowired
 TheatreService theaterServ;

 @Autowired
 BookingTableRepo bookRepo;

 public String user;
 public String adminn;

 @Autowired
 AdminRepository adRep;

 @GetMapping("/")
 public String index() {
  return "Home";
 }

 @GetMapping("/home")
 public String indexHome(Model m) {
  m.addAttribute("msg", "Invalid UserName or Password");
  return "Home";
 }

 @GetMapping("/loginPage")
 public String showLoginForm() {
  return "/";
 }

 @PostMapping("/loginPage")
 public String submitLoginForm(@RequestParam String userID, @RequestParam String password, HttpSession session,
                               Model m) {
  LoginCredentials log = logRepo.findByUserID(userID);
  Admin ad = adRep.findByUsername(userID);
  m.addAttribute("msg", "Invalid UserName or Password");
  if (log == null && ad == null) {
   return "redirect:/home";
  } else if (log == null) {
   if (!ad.getUsername().equals(userID) || !ad.getAdminPass().equals(password)) {
    return "redirect:/home";
   }
   if (ad.getUsername().equals(userID) && ad.getAdminPass().equals(password)) {
    adminn = userID;
    session.setAttribute("admin", adminn);
    return "redirect:/TheatreTable/List";
   }
  } else if (ad == null && !log.getPassword().equals(password)) {
   return "redirect:/home";
  } else {
   user = userID;
   session.setAttribute("userID", userID);
   return "redirect:/movies";
  }
  return "redirect:/home";
 }

 @GetMapping("/updatePass")
 public String update(HttpSession session) {
  if (session.getAttribute("userID") == null) {
   return "redirect:/";
  }
  return "updatePass";
 }

 @PostMapping("/updatePass")
 public String Updatedpass(@RequestParam String pass, Model m, HttpSession session) {
  String useri = (String) session.getAttribute("userID");
  LoginCredentials log = logRepo.findByUserID(useri);
  log.setPassword(pass);
  logRepo.save(log);
  m.addAttribute("msg", "Updated Password Successfully");
  return "updatePass";
 }

 @GetMapping("/register")
 public String register(Model m) {
  m.addAttribute("customer", new CustomerDetails());
  m.addAttribute("lg", new LoginCredentials());
  return "register";
 }

 @RequestMapping("/loginInsert")
 public ModelAndView loginInsert(@ModelAttribute("customer") CustomerDetails customer,
                                 @ModelAttribute("lg") LoginCredentials lg, @RequestParam String password) {
  ModelAndView mv = new ModelAndView();
  custRepo.save(customer);
  System.out.println("gvgjhg");
  Random random = new Random();
  int randomnum = random.nextInt(9000) + 1000;
  String name = customer.getName();
  String custIdString = name.substring(0, 2).toUpperCase() + randomnum;
  String pass = password;
  lg.setCustomerID(custIdString);
  lg.setPassword(pass);
  lg.setCustomer(customer);
  logRepo.save(lg);
  String s = lg.getCustomerID();
  String ss = lg.getPassword();
  mv.addObject("cust", s);
  mv.addObject("pass", ss);
  mv.setViewName("userLoginDetails");
  System.out.println(lg.getId());
  mv.setViewName("userLoginDetails");
  return mv;
 }

 @GetMapping("/userLoginDetails")
 public String loginDetails() {
  return "userLoginDetails";
 }

 @GetMapping("/movies")
 public String listMovies(Model model, HttpSession session) {
  model.addAttribute("movies", moviSer.listMovies());
  return "movies";
 }

 @GetMapping("/movies/{id}")
 public String booktMovie(@PathVariable("id") Integer id, HttpSession session, Model model, BookingTable book) {
  if (user == null || session.getAttribute("userID") == null) {
   return "redirect:/";
  }

  MovieList movie = moviSer.findByMovieId(id);
  model.addAttribute("movie", movie);
  model.addAttribute("bookingId", book);
  model.addAttribute("availableSeats", movie.getAvailabilityOfSeats());

  int remainingCapacity = movie.getScreenCapacity() - bookRepo.getTotalBookedSeatsForMovie(movie.getMovieId());
  model.addAttribute("availableSeats", remainingCapacity);

  // Add an attribute to store the error message if it exists
  if (session.getAttribute("errorMsg") != null) {
   model.addAttribute("errorMsg", session.getAttribute("errorMsg"));
   session.removeAttribute("errorMsg");
  }
  return "booking";
 }
 @PostMapping("/movies/{id}")
 public String bookedMovie(@ModelAttribute("bookingId") BookingTable book, HttpSession session, Model model, BindingResult bindingResult) {
  if (user == null || session.getAttribute("userID") == null) {
   return "redirect:/";
  }
  MovieList movie = moviSer.findByMovieId(book.getMovieId());
  int bookedSeats = bookRepo.getTotalBookedSeatsForMovie(book.getMovieId()) + book.getNumOfSeats();

  if (bookedSeats <= movie.getScreenCapacity()) {
   book.setMovieName(movie.getMovieName());
   book.setTheaterName(movie.getTheater().getThatreName());
   book.setPrice(book.getPrice() * book.getNumOfSeats());
   book.setUserID(user);
   bookRepo.save(book);
   movie.setAvailabilityOfSeats(movie.getAvailabilityOfSeats()-book.getNumOfSeats());
   movieRepo.save(movie);
   model.addAttribute("book", "Booked successfully");
   model.addAttribute("booking", book);
   return "redirect:/myBookings";
  } else {
   // Display error message when requested seats exceed available capacity
   bindingResult.rejectValue("numOfSeats", "error.numOfSeats", "Seats exceed the available capacity. Please try again.");
   model.addAttribute("movie", movie);
   model.addAttribute("bookingId", book);
   model.addAttribute("availableSeats", movie.getAvailabilityOfSeats());
   session.setAttribute("errorMsg", "Seats exceed the available capacity. Please try again.");
   return "booking";
  }
 }

 @PostMapping("/myBookings")
 public String myBookings(Model m, BookingTable book, HttpSession session) {
  if (user == null || session.getAttribute("userID") == null) {
   return "redirect:/";
  }
  List < BookingTable > booki = bookRepo.findAllByUserID(user);
  m.addAttribute("book", "Booked successfully!!!");
  m.addAttribute("bookings", booki);
  return "myBookings";
 }

 @GetMapping("/myBookings")
 public String myiBookings(Model m, BookingTable book, HttpSession session) {
  if (user == null || session.getAttribute("userID") == null) {
   return "redirect:/";
  }
  List < BookingTable > booki = bookRepo.findAllByUserID(user);
  m.addAttribute("bookings", booki);
  return "myBookings";
 }

 @GetMapping("/logout")
 public String logout(HttpSession session) {
  session.invalidate();
  return "Home";
 }

 @GetMapping("/allBookings")
 public String allUserBookings(Model m, BookingTable book, HttpSession session) {
  if (session.getAttribute("admin") == null) {
   return "redirect:/";
  }
  List < BookingTable > booki = bookRepo.findAll();
  m.addAttribute("bookings", booki);
  return "allBookings";
 }
}