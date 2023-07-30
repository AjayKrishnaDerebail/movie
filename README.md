<div align="justify">

# Online Cinema Ticket Booking System

The Online Cinema Ticket Booking System is a web-based application developed using Spring Boot and various technologies such as core Java, RESTful web services, Hibernate, JSP, HTML, and CSS. This system enables users to browse available movies, select theatres, and book movie tickets seamlessly. It also includes features like CRUD operations for managing movie and theatre data, as well as a sign-in page with session management.

## Features

- Browse available movies: Users can view a list of movies currently playing in theatres.
- Select theatres: Users can select their preferred theatre from available options.
- Book movie tickets: Users can choose a movie, select the desired showtime, and book tickets online.
- CRUD operations: Administrators have the ability to perform Create, Read, Update, and Delete operations on movie and theatre data.
- Sign-in page: The system provides a secure sign-in page for users to authenticate themselves before accessing the ticket booking features.
- Session management: User sessions are managed to ensure a seamless and secure browsing experience.

## Installation

To run the Online Cinema Ticket Booking System locally, follow these steps:

1. Clone the repository:

```
git clone https://github.com/Sateesh079/Online_Cinema_Ticket_Booking_System.git
```

2. Navigate to the project directory:

```
cd online-cinema-ticket-booking
```

3. Build and run the application using Maven:

```
mvn spring-boot:run
```

4. Access the application in your web browser:

```
http://localhost:8082
```

## Usage

1. Landing Page: Upon accessing the application, you will be greeted with the landing page displaying current movies and theaters.

2. Movie Selection: Browse the list of movies and click on a movie to view its details, showtimes, and available theaters.

3. Theater Selection: Select your preferred theater and choose a showtime for the movie you want to watch.

4. Booking: Provide the required details such as the number of tickets and proceed to complete the booking process.

5. CRUD Operations: Administrators can log in to access the admin dashboard. From there, they can add, update, or delete movie and theater information as needed. Customer Can login an perform customer operations

## Configuration

The Online Cinema Ticket Booking System can be configured using the following files:

- `application.properties`: This file contains configuration properties such as the database connection details, server port, and other application-specific settings.

</div>
