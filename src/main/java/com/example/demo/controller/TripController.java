package com.example.demo.controller;

import com.example.demo.dto.ContinentDTO;
import com.example.demo.dto.CountryDTO;
import com.example.demo.dto.TripDTO;
import com.example.demo.enums.TripType;
import com.example.demo.model.Trip;
import com.example.demo.service.ConverterService;
import com.example.demo.service.TripService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TripDTO.class)))}),
            @ApiResponse(responseCode = "500", description = "List of promoted trips was not displayed.")
    })

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

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TripDTO.class)))}),
            @ApiResponse(responseCode = "500", description = "List of upcoming trips was not displayed.")
    })

    @GetMapping("/upcoming-trips")
    public ResponseEntity<?> getTripsUpcomingByContinentAndCountry(
            @Parameter(name = "continent", description = "Choose a continent where You are going to go.")
            @RequestParam(value = "continent-name", required = false) String continentName,
            @Parameter(description = "Choose a country where You are going to go.")
            @RequestParam(value = "country-name", required = false) String countryName) {

        log.info("Wycieczki zbliżające się według wybranych kontynentów lub krajów: ");

        try{
            return ResponseEntity.ok(tripService.getUpcomingTripsByContinentNameOrCountryName(continentName, countryName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + e.getMessage());
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TripDTO.class)))}),
            @ApiResponse(responseCode = "500", description = "List of last purchased trips was not displayed.")
    })

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

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TripDTO.class)))}),
            @ApiResponse(responseCode = "500", description = "List of trips search by given parameters was not displayed.")
    })

    @GetMapping("/search-trips")
    public ResponseEntity<?> searchTripsByGivenParameters(
            @Parameter(name = "continent", description = "Choose a continent where You are going to go.")
            @RequestParam(value = "continent-name", required = false) String continentName,
            @Parameter(name = "country", description = "Choose a country where You are going to go.")
            @RequestParam(value = "country-name", required = false) String countryName,
            @Parameter(name = "city of start", description = "Choose a city You want to departure.")
            @RequestParam(value = "city-of-departure", required = false) String cityOfDeparture,
            @Parameter(name = "airport of start", description = "Choose a airport in IATA code You want to departure.")
            @RequestParam(value = "airport-of-departure", required = false) String airportOfDeparture,
            @Parameter(name = "destination city", description = "Choose a city You want to visit.")
            @RequestParam(value = "city-of-destination", required = false) String cityOfDestination,
            @Parameter(name = "destination airport", description = "Choose a airport You want to end your flight.")
            @RequestParam(value = "airport-of-destination", required = false) String airportOfDestination,
            @Parameter(name = "date from", description = "Choose a date when You could start Your trip.",
                    content = { @Content(schema = @Schema(implementation = LocalDate.class))})
            @RequestParam(value = "date-of-departure", required = false) LocalDate dateOfDeparture,
            @Parameter(name = "date to", description = "Choose a latest date when You could end Your trip.",
                    content = { @Content(schema = @Schema(implementation = LocalDate.class))})
            @RequestParam(value = "date-of-return", required = false) LocalDate dateOfReturn,
            @Parameter(name = "hotel standard", description = "Choose a standard of Your destination hotel.",
                    content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TripType.class)))})
            @RequestParam(value = "type-of-trip", required = false) TripType typeOfTrip,
            @Parameter(name = "standard in stars", description = "Choose a standard of Your hotel given in stars.")
            @RequestParam(value = "hotel-number-of-stars", required = false) Byte numberOfStars,
            @Parameter(name = "trip time", description = "Choose a length of Your tour.")
            @RequestParam(value = "number-of-days", required = false) Short numberOfDays
            ) {

        log.info("Wyszukiwanie wycieczek według zadanych kryteriów: ");

        try {
            return ResponseEntity.ok(tripService.getTripsByParameters(continentName, countryName, cityOfDeparture, airportOfDeparture,
                    cityOfDestination, airportOfDestination, dateOfDeparture, dateOfReturn, typeOfTrip, numberOfStars, numberOfDays));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + exception.getMessage());
        }

    }
}
