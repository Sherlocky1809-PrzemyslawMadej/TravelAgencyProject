package com.example.demo.RESTcontroller;

import com.example.demo.dto.TripDTO;
import com.example.demo.enums.TripType;
import com.example.demo.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Tag(name = "Main Page", description = "This is main page to start trip search")
@RestController
@Slf4j
@RequestMapping("/main")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    @Operation(
            summary = "Promoted trips",
            description = "This is a GET endpoint for display few of promoted trips."
    )
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


    @Operation(
            summary = "Upcoming trips",
            description = "This is a GET endpoint for display upcoming trips from given country or continent."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TripDTO.class)))}),
            @ApiResponse(responseCode = "500", description = "List of upcoming trips was not displayed.")
    })
    @GetMapping("/upcoming-trips")
    public ResponseEntity<?> getTripsUpcomingByContinentAndCountry(
            @Parameter(description = "Choose a continent where You are going to go.")
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


    @Operation(
            summary = "Last purchased trips",
            description = "This is a GET endpoint for display last purchased trips by clients."
    )
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


    @Operation(
            summary = "Search trips",
            description = "This is a GET endpoint for searching trips by given parameters."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500", description = "List of trips search by given parameters was not displayed.")
    })
    @GetMapping("/search-trips")
    public ResponseEntity<?> searchTripsByGivenParameters(
            @Parameter(description = "Choose a continent where You are going to go.")
            @RequestParam(value = "continent-name", required = false) String continentName,
            @Parameter(description = "Choose a country where You are going to go.")
            @RequestParam(value = "country-name", required = false) String countryName,
            @Parameter(description = "Choose a city You want to departure.")
            @RequestParam(value = "city-of-departure", required = false) String cityOfDeparture,
            @Parameter(description = "Choose a airport in IATA code You want to departure.")
            @RequestParam(value = "airport-of-departure", required = false) String airportOfDeparture,
            @Parameter(description = "Choose a city You want to visit.")
            @RequestParam(value = "city-of-destination", required = false) String cityOfDestination,
            @Parameter(description = "Choose a airport You want to end your flight.")
            @RequestParam(value = "airport-of-destination", required = false) String airportOfDestination,
            @Parameter(description = "Choose a date when You could start Your trip ex. 2023-06-01")
            @RequestParam(value = "date-of-departure", required = false) LocalDate dateOfDeparture,
            @Parameter(description = "Choose a latest date when You could end Your trip ex. 2023-06-15")
            @RequestParam(value = "date-of-return", required = false) LocalDate dateOfReturn,
            @Parameter(description = "Choose a standard of Your destination hotel.")
            @RequestParam(value = "type-of-trip", required = false) TripType typeOfTrip,
            @Parameter(description = "Choose a standard of Your hotel given in stars.")
            @RequestParam(value = "hotel-number-of-stars", required = false) Byte numberOfStars,
            @Parameter(description = "Choose a length of Your tour.")
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
