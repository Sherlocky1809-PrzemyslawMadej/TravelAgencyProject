package com.example.demo.controller;

import com.example.demo.dto.TripDTO;
import com.example.demo.enums.TripType;
import com.example.demo.model.Trip;
import com.example.demo.service.ConverterService;
import com.example.demo.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/main")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/trips-promoted")
    public ResponseEntity<?> getTripsPromoted() {

        log.info("Wycieczki promowane: ");

        try{
            return ResponseEntity.ok(tripService.getTripsPromoted());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + e.getMessage());
        }
    }

    @GetMapping("/upcoming-trips")
    public ResponseEntity<?> getTripsUpcomingByContinentAndCountry(
            @RequestParam(value = "continent-name", required = false) String continentName,
            @RequestParam(value = "country-name", required = false) String countryName) {

        log.info("Wycieczki zbliżające się według wybranych kontynentów lub krajów: ");

        try{
            return ResponseEntity.ok(tripService.getUpcomingTripsByContinentNameOrCountryName(continentName, countryName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + e.getMessage());
        }
    }

    @GetMapping("/last-purchased-trips")
    public ResponseEntity<?> getTripsLastPurchased() {

        log.info("Wycieczki ostatnio zakupione: ");


        try{
            return ResponseEntity.ok(tripService.getTripsLastPurchased());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + e.getMessage());
        }
    }

    @GetMapping("/search-trips")
    public ResponseEntity<?> searchTripsByGivenParameters(
            @RequestParam(value = "continent-name", required = false) String continentName,
            @RequestParam(value = "country-name", required = false) String countryName,
            @RequestParam(value = "city-of-departure", required = false) String cityOfDeparture,
            @RequestParam(value = "airport-of-departure", required = false) String airportOfDeparture,
            @RequestParam(value = "city-of-destination", required = false) String cityOfDestination,
            @RequestParam(value = "airport-of-destination", required = false) String airportOfDestination,
            @RequestParam(value = "date-of-departure", required = false) LocalDate dateOfDeparture,
            @RequestParam(value = "date-of-destination", required = false) LocalDate dateOfDestination,
            @RequestParam(value = "type-of-trip", required = false) TripType typeOfTrip,
            @RequestParam(value = "hotel-number-of-stars", required = false) Byte numberOfStars,
            @RequestParam(value = "number-of-days", required = false) Short numberOfDays
            ) {

        log.info("Wyszukiwanie wycieczek według zadanych kryteriów: ");

        try {
            return ResponseEntity.ok(tripService.getTripsByParameters(continentName, countryName, cityOfDeparture, airportOfDeparture,
                    cityOfDestination, airportOfDestination, dateOfDeparture, dateOfDestination, typeOfTrip, numberOfStars, numberOfDays));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + exception.getMessage());
        }

    }
}
