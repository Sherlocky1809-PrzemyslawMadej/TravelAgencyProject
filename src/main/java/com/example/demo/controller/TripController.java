package com.example.demo.controller;

import com.example.demo.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
