<%@ page language="java" contentType="text/html; charset=UTF-8"

 pageEncoding="UTF-8"%><%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%--
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Booking: ${movie.name}</title>
		</head>
		<body>
			<h1>Booking: ${movie.name}</h1>
			<c:forEach var="theatre" items="${theatres}">
				<!-- Add your booking form here -->
			</body>
		</html> --%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Booking Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" cr
    ossorigin="anonymous">
    <style>
      .topnav {
        overflow: hidden;
        background-color: #333;
      }

      .topnav a {
        float: left;
        color: #f2f2f2;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 17px;
      }

      .topnav a:hover {
        background-color: #ddd;
        color: black;
      }

      .topnav a.active {
        background-color: #04AA6D;
        color: white;
      }

      main {
        margin-top: 50px;
      }

      .d-inline-flex p-2 {
        margin: auto;
        width: 60%;
        border: 3px solid #73AD21;
        padding: 10px;
      }
    </style>
  </head>
  <body>
    <!-- ${username.customer.getName() }

 -->
    <h3 style="color: green;">${book}</h3>
    <div class="topnav">
      <!-- <a href="/TheatreTable/List">All Theater</a>

 -->
      <a href="/movies">Home</a>
      <a href="/updatePass"> Update Password</a>
      <a href="/logout">Logout</a>
    </div><%-- ${movie.theater.getThatreName()} --%><%-- ${movie.theater}

 --%><%-- ${movie.movieName } --%><%--
						<input style="color: red;" type="text"

 placeholder="${movie.movieName }" readonly> --%> <h1>Booking</h1>
    <div class="container">
      <sp:form action="/movies/{id}" method="post" modelAttribute="bookingId">
      <span id="errorMsg" style="color: red">${errorMsg}</span>
        <div class="d-inline-flex p-2">
          <br>Movie Name <br>
          <sp:input path="movieName" value="${movie.movieName }" class="form-control d-inline-flex p-2" type="text" placeholder="${movie.movieName }" readonly="true" />
          <sp:input type="hidden" path="movieId" value="${movie.movieId }" class="form-control"  />
          <br>
        </div>
        <div class="d-inline-flex p-2">
          <br> Theater Name <br>
          <sp:input path="theaterName" value="${movie.theater.getThatreName()}" class="form-control" type="text" placeholder="${movie.theater.getThatreName()}" readonly="true" />
          <br>
        </div>
        <br><%--
																<label for="theatreName">Select a theater:</label>
																<select name="theaterName" id="theaterName">
																	<c:forEach var="name" items="${theatres.thatreName}">
																		<option value="${name}">${name}</option>
																	</c:forEach>
																</select> --%> <div class="d-inline-flex p-2"> Name &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <br>
          <sp:input path="name" class="form-control" placeholder="Enter your Name" />
        </div>
        <br>
                <div class="d-inline-flex p-2">
                    <label for="numOfSeats">Select Number of Seats</label>
                    <br>
                    <br>
                    <sp:input path="numOfSeats" class="form-control" placeholder="Enter number of seats" />
                    <form:errors path="numOfSeats" cssClass="text-danger" />

                    <div class="d-inline-flex p-2">
                        <p>Available Seats: ${availableSeats}</p>
                    </div>
                </div>


        <br>
        <div class="d-inline-flex p-2">
          <label for="exampleFormControlSelect1">Show Time</label>
          <sp:select path="showTime" class="form-control" id="exampleFormControlSelect1">
            <option>9:00 AM-12:00 PM</option>
            <option>12:30 PM-3:30 PM</option>
            <option>5:00 PM-8:00 PM</option>
            <option>9:00 PM-12:00 AM</option>
          </sp:select>
        </div>

        <br>
        <div class="d-inline-flex p-2">
          <label>Select Price</label>
          <sp:select path="price" class="form-control" id="exampleFormControlSelect1">
            <option>150</option>
            <option>250</option>
          </sp:select>
        </div>
        <br>

        <button type="submit" class="btn btn-primary">Book</button>
      </sp:form>
       <script>
              // Function to hide the error message on page load
              window.onload = function() {
                  var errorMsg = document.getElementById("errorMsg");
                  if (errorMsg.textContent.trim().length > 0) {
                      errorMsg.style.display = "inline";
                  } else {
                      errorMsg.style.display = "none";
                  }

              };

              // Function to check the form submission
              document.getElementById("bookingForm").onsubmit = function(e) {
                  var availableSeats = /* Get the available seats value here, you can store it in a hidden input field in your form */
                  var selectedSeats = /* Get the number of seats selected by the user here */

                  var errorMsg = document.getElementById("errorMsg");

                  if (selectedSeats > availableSeats) {
                      e.preventDefault(); // Prevent form submission
                      errorMsg.textContent = "Seats exceed the available capacity. Please try again.";
                      errorMsg.style.display = "inline";
                  } else {
                      errorMsg.style.display = "none";
                  }
                 // Function to redirect to the same page when refreshing
                         if (window.history.replaceState) {
                             window.history.replaceState(null, null, window.location.href);
                         }

              };
          </script>
    </div>
  </body>
</html>