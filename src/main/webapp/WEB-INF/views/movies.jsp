<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>All Movies</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
      h3 {
        text-align: centre;
        margin: :20px;
        colour: red
      }

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
      .btn-disabled {
         background-color: #ccc;
         cursor: not-allowed;
      }
    </style>
  </head>
  <body> ${msg} <div class="topnav">
      <!-- <a href="/TheatreTable/List">All Theater</a>

 -->
      <a href="/logout">Logout</a>
      <a href="/updatePass"> Update Password</a>
      <a href="/myBookings">My Bookings</a>
    </div><%-- <%out.print(session.getAttribute("userID")); %> --%> <h1 style="text-align: center">All Movies</h1>
    <div class="container">
      <table class="table">
        <thead class="table-dark">
          <tr>
            <!-- <th>ID</th> -->
            <th>Name</th>
            <th>Genre</th>
            <th>Theatre Name</th>
            <th>Screen No</th>
            <th>Total Capacity</th>
            <th>Available Capacity</th>
            <th>Availability</th>
          </tr>
        </thead>
        <c:forEach var="movie" items="${movies}">
          <tr><%--
			<td>${movie.movieId}</td> --%> <td>${movie.movieName}</td>
            <td>${movie.genre}</td>
            <td>${movie.theater.thatreName}</td>
            <td>${movie.screen_id}</td>
            <td>${movie.screenCapacity}</td>
            <td>${movie.availabilityOfSeats}</td>
             <td>
                          <!-- Disable the "Book Now" button if availableSeats is zero -->
                          <c:choose>
                            <c:when test="${movie.availabilityOfSeats > 0}">
                              <a href="/movies/${movie.movieId}" class="btn btn-warning">Book Now</a>
                            </c:when>
                            <c:otherwise>
                              <button class="btn btn-disabled" disabled>Book Now</button>
                            </c:otherwise>
                          </c:choose>
                        </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body>
</html>