package com.movie.repository;



import java.util.List;


import com.movie.bean.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;



import com.movie.bean.BookingTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookingTableRepo extends JpaRepository<BookingTable, Long> {



 List<BookingTable> findAllByUserID(String userID);
 @Query("SELECT COALESCE(SUM(b.numOfSeats), 0) FROM BookingTable b WHERE b.movieId = :movieId")
 int getTotalBookedSeatsForMovie(@Param("movieId") Integer movieId);




}

