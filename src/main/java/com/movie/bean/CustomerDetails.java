package com.movie.bean;

import jakarta.persistence.*;

@Entity
@Table(name = "CustomerDetails" )

public class CustomerDetails {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "customer_id", nullable = false, unique = true)
 public int customerID;
 private String name;
 private String gender;
 private String presentAddress;

 private String phoneNumber;

 private String email;

 public CustomerDetails() {
    super();
 }

 public CustomerDetails (int customerID, String name, String gender, String presentAddress, String phoneNumber, String email) {
  this.customerID = customerID;
  this.name = name;
  this.gender = gender;
  this.presentAddress = presentAddress;
  this.phoneNumber = phoneNumber;
  this.email = email;
 }

 public int getCustomerID () {
  return customerID;
 }

 public void setCustomerID (int customerID) {
  this.customerID = customerID;
 }

 public String getName () {
  return name;
 }

 public void setName (String name) {
  this.name = name;
 }

 public String getGender () {
  return gender;
 }

 public void setGender (String gender) {
  this.gender = gender;
 }

 public String getPresentAddress () {
  return presentAddress;
 }

 public void setPresentAddress (String presentAddress) {
  this.presentAddress = presentAddress;
 }

 public String getPhoneNumber () {
  return phoneNumber;
 }

 public void setPhoneNumber (String phoneNumber) {
  this.phoneNumber = phoneNumber;
 }

 public String getEmail () {
  return email;
 }

 public void setEmail (String email) {
  this.email = email;
 }

 @Override
 public String toString () {
  return "CustomerDetails{" +
          "customerID=" + customerID +
          ", name='" + name + '\'' +
          ", gender='" + gender + '\'' +
          ", presentAddress='" + presentAddress + '\'' +
          ", phoneNumber='" + phoneNumber + '\'' +
          ", email='" + email + '\'' +
          '}';
 }
}