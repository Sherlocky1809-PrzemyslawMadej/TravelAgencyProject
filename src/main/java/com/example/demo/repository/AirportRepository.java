package com.example.demo.repository;

import com.example.demo.model.Airport;
import com.example.demo.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    @Query(value = "SELECT * FROM airport JOIN city ON city.city_id = airport.city_city_id " +
            "WHERE (?1 is null OR LOWER(airport_name) LIKE %?1%) AND (?2 is null OR LOWER(city.city_name) LIKE %?2%)",
            nativeQuery = true)
    List<Airport> findAllByParameters(String airportName, String cityName);
}
