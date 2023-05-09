package com.example.demo.repository;

import com.example.demo.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    Optional<Destination> findByDestinationId(Integer destinationId);

    @Query(value = "SELECT * FROM destination JOIN city ON city.city_id = destination.destination_city_city_id " +
            "JOIN airport ON airport.airport_id = destination.destination_airport_airport_id" +
            " WHERE (?1 is null OR LOWER(city.city_name) LIKE %?1%) AND (?2 is null OR LOWER(airport.airport_name) LIKE %?2%) LIMIT 5",
    nativeQuery = true)
    List<Destination> findAllByParameters(String cityName, String airportName);
}
