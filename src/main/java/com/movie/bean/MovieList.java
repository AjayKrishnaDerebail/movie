package com.movie.bean;


import jakarta.persistence.*;

@Entity
@Table(name="movies" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"theater", "screen_id"})
        })
public class MovieList {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer movieId;
 @Column(name="moviename")
 private String movieName;
 private String genre;
 @Column(name = "screen_id")
 private int screen_id;

 @ManyToOne
 @JoinColumn(name="theater")
 private Theatre theater;

 private int screenCapacity;

 public MovieList() {

 super();

 }

 public MovieList (Integer movieId, String movieName, String genre, int screen_id, Theatre theater, int screenCapacity) {
  this.movieId = movieId;
  this.movieName = movieName;
  this.genre = genre;
  this.screen_id = screen_id;
  this.theater = theater;
  this.screenCapacity = screenCapacity;
 }

 public Integer getMovieId() {
  return movieId;
 }

 public void setMovieId(Integer movieId) {
  this.movieId = movieId;
 }

 public String getMovieName() {
  return movieName;
 }

 public void setMovieName(String movieName) {
  this.movieName = movieName;
 }

 public String getGenre() {
  return genre;
 }
 public void setGenre(String genre) {
  this.genre = genre;
 }

 public int getScreen_id () {
  return screen_id;
 }

 public void setScreenId (int screen_id) {
  this.screen_id = screen_id;
 }

 public Theatre getTheater() {
 return theater;

 }

 public void setTheater(Theatre theater) {
  this.theater = theater;
 }

 public int getScreenCapacity () {
  return screenCapacity;
 }

 public void setScreenCapacity (int screenCapacity) {
  this.screenCapacity = screenCapacity;
 }

 @Override
 public String toString () {
  return "MovieList{" +
          "movieId=" + movieId +
          ", movieName='" + movieName + '\'' +
          ", genre='" + genre + '\'' +
          ", screen_id=" + screen_id +
          ", theater=" + theater +
          ", screenCapacity=" + screenCapacity +
          '}';
 }
}

