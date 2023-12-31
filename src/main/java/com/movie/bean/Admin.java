package com.movie.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "adminTable")
public class Admin {
 public Admin() {

  super();

 }
 @Id
 private Long id;
 private String username;
 private String adminPass;

 public Admin (Long id, String username, String adminPass) {
  this.id = id;
  this.username = username;
  this.adminPass = adminPass;
 }

 public Long getId () {
  return id;
 }

 public void setId (Long id) {
  this.id = id;
 }

 public String getUsername () {
  return username;
 }

 public void setUsername (String username) {
  this.username = username;
 }

 public String getAdminPass () {
  return adminPass;
 }

 public void setAdminPass (String adminPass) {
  this.adminPass = adminPass;
 }

 @Override
 public String toString () {
  return "Admin{" +
          "id=" + id +
          ", username='" + username + '\'' +
          ", adminPass='" + adminPass + '\'' +
          '}';
 }
}