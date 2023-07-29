package com.movie.bean;

import jakarta.persistence.*;

@Entity
public class BookingTable {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Integer bookingId;
 private String name;
 private String theaterName;
 private String movieName;
 private String showTime;
 private int numOfSeats;


 private int price;
 private String userID;

 private Integer movieId;

 public BookingTable () {
  super();
 }

 public BookingTable (Integer bookingId,Integer movieId, String name, String theaterName, String movieName, String showTime, int numOfSeats, int price, String userID) {
  this.bookingId = bookingId;
  this.name = name;
  this.theaterName = theaterName;
  this.movieName = movieName;
  this.showTime = showTime;
  this.numOfSeats = numOfSeats;
  this.price = price;
  this.userID = userID;
  this.movieId=movieId;
 }

 public Integer getBookingId () {
  return bookingId;
 }

 public Integer getMovieId() {
  return movieId;
 }

 public void setMovieId(Integer movieId) {
  this.movieId = movieId;
 }
 public void setBookingId (Integer bookingId) {
  this.bookingId = bookingId;
 }

 public String getName () {
  return name;
 }

 public void setName (String name) {
  this.name = name;
 }

 public String getTheaterName () {
  return theaterName;
 }

 public void setTheaterName (String theaterName) {
  this.theaterName = theaterName;
 }

 public String getMovieName () {
  return movieName;
 }

 public void setMovieName (String movieName) {
  this.movieName = movieName;
 }

 public String getShowTime () {
  return showTime;
 }

 public void setShowTime (String showTime) {
  this.showTime = showTime;
 }

 public int getNumOfSeats () {
  return numOfSeats;
 }

 public void setNumOfSeats (int numOfSeats) {
  this.numOfSeats = numOfSeats;
 }

 public int getPrice () {
  return price;
 }

 public void setPrice (int price) {
  this.price = price;
 }

 public String getUserID () {
  return userID;
 }

 public void setUserID (String userID) {
  this.userID = userID;
 }

 @Override
 public String toString () {
  return "BookingTable{" +
          "bookingId=" + bookingId +
          ", name='" + name + '\'' +
          ", theaterName='" + theaterName + '\'' +
          ", movieName='" + movieName + '\'' +
          ", showTime='" + showTime + '\'' +
          ", numOfSeats=" + numOfSeats +
          ", price=" + price +
          ", userID='" + userID + '\'' +
          '}';
 }

}