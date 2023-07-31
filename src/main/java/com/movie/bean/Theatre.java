package com.movie.bean;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Theatre {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name="theater_Id")
 private Integer theatreId;

 @Column(unique = true)
 private String thatreName;


 private String city;

 public Theatre() {
 super();
 }
 @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<MovieList> movies = new ArrayList<>();

 public Theatre (Integer theatreId, String thatreName, String city) {
  this.theatreId = theatreId;
  this.thatreName = thatreName;

  this.city = city;
 }

 public Integer getTheatreId () {
  return theatreId;
 }

 public void setTheatreId (Integer theatreId) {
  this.theatreId = theatreId;
 }


 public String getThatreName () {
  return thatreName;
 }

 public void setThatreName (String thatreName) {
  this.thatreName = thatreName;
 }


 public String getCity () {
  return city;
 }

 public void setCity (String city) {
  this.city = city;
 }

 @Override
 public String toString () {
  return "Theatre{" +
          "theatreId=" + theatreId +
          ", thatreName='" + thatreName + '\'' +
          ", city='" + city + '\'' +
          '}';
 }
}

