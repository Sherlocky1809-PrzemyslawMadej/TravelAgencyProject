package com.example.demo.repository;

import com.example.demo.model.Departure;
import com.example.demo.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Integer> {

    Optional<Departure> findByDepartureId(Integer departureId);

    @Query(value = "SELECT * FROM departure JOIN city ON city.city_id = departure.departure_city_city_id " +
            "JOIN airport ON airport.airport_id = departure.departure_airport_airport_id" +
            " WHERE (?1 is null OR LOWER(city.city_name) LIKE %?1%) AND (?2 is null OR LOWER(airport.airport_name) LIKE %?2%) LIMIT 5",
    nativeQuery = true)
    List<Departure> findAllByParameters(String cityName, String airportName);
}
