package com.example.demo.repository;

import com.example.demo.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "SELECT * FROM trip JOIN departure ON departure.departure_id = trip.departure_departure_id" +
            "JOIN destination ON destination.destination_id = trip.destination_destination_id " +
            "JOIN hotel ON hotel.hotel_id = trip.destination_hotel_hotel_id WHERE (is_promoted = true) LIMIT 5", nativeQuery = true)
    List<Trip> findAllPromoted();

    @Query(value = "SELECT * FROM trip JOIN departure ON departure.departure_id = trip.departure_departure_id" +
            "JOIN destination ON destination.destination_id = trip.destination_destination_id " +
            "JOIN hotel ON hotel.hotel_id = trip.destination_hotel_hotel_id WHERE (is_promoted = true) LIMIT 5", nativeQuery = true)
    List<Trip> findAllByParameters();
}
