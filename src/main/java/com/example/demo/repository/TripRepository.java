package com.example.demo.repository;

import com.example.demo.enums.TripType;
import com.example.demo.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "SELECT * FROM trip JOIN departure ON departure.departure_id = trip.departure_departure_id " +
            "JOIN destination ON destination.destination_id = trip.destination_destination_id " +
            "JOIN hotel ON hotel.hotel_id = trip.destination_hotel_hotel_id WHERE (is_promoted = true) LIMIT 5", nativeQuery = true)
    List<Trip> findAllPromoted();

    Optional<Trip> findByTripId(Long tripId);

    @Query(value = "SELECT * FROM trip JOIN departure ON departure.departure_id = trip.departure_departure_id " +
            "JOIN destination ON destination.destination_id = trip.destination_destination_id " +
            "JOIN hotel ON hotel.hotel_id = trip.destination_hotel_hotel_id " +
            "JOIN city AS departure_city ON departure_city.city_id = departure.departure_city_city_id " +
            "JOIN airport AS departure_airport ON departure_airport.airport_id = departure.departure_airport_airport_id " +
            "JOIN city AS destination_city ON destination_city.city_id = destination.destination_city_city_id " +
            "JOIN airport AS destination_airport ON destination_airport.airport_id = destination.destination_airport_airport_id " +
            "JOIN city AS hotel_city ON hotel_city.city_id = hotel.city_city_id " +
            "JOIN country AS hotel_city_country ON hotel_city_country.country_id = hotel_city.country_country_id " +
            "JOIN continent AS hotel_city_country_continent ON hotel_city_country_continent.id = hotel_city_country.continent_id " +
            "WHERE (?1 is null OR LOWER(hotel_city_country_continent.continent_name) LIKE %?1%) " +
            "AND (?2 is null OR LOWER(hotel_city_country.country_name) LIKE %?2%) " +
            "AND (?3 is null OR LOWER(departure_city.city_name) LIKE %?3%)" +
            "AND (?4 is null OR LOWER(departure_airport.airport_name) LIKE %?4%) " +
            "AND (?5 is null OR LOWER(destination_city.city_name) LIKE %?5%) " +
            "AND (?6 is null OR LOWER(destination_airport.airport_name) LIKE %?6%) " +
            "AND (?7 is null OR date_of_departure >= ?7) AND (?8 is null OR date_of_return >= ?8) " +
            "AND (?9 is null OR UPPER(trip_type) = ?9) AND (?10 is null OR hotel.number_of_stars = ?10) " +
            "AND (?11 is null OR number_of_days = ?11) LIMIT 5", nativeQuery = true)

    List<Trip> findAllByGivenParameters(String continentName, String countryName, String cityOfDeparture, String airportOfDeparture, String cityOfDestination,
                                        String airportOfDestination, LocalDate dateOfDeparture, LocalDate dateOfReturn,
                                        TripType typeOfTrip, Byte hotelNumberOfStars, Short numberOfDays);


}
