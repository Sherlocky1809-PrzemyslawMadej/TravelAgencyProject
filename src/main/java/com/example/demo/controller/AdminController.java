package com.example.demo.controller;

import com.example.demo.dto.TripDTO;
import com.example.demo.model.Trip;
import com.example.demo.service.TripService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/trip-configuration")
public class AdminController {

    private final TripService tripService;

    public AdminController(TripService tripService) {
        this.tripService = tripService;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TripDTO.class))}),
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = TripDTO.class))},
                    description = "New trip was created"),
            @ApiResponse(responseCode = "500", description = "New trip was not created.")
    })

    @PostMapping("/add-trip")
    public ResponseEntity<?> addTripToRepo(
           @Parameter(required = true, description = "This is a trip created in order to add to repo.") @Valid @RequestBody Trip trip
    ) {

    log.info("DODANIE NOWEJ WYCIECZKI: ");

        try {
            return ResponseEntity.ok(tripService.addTrip(trip));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + exception.getMessage());
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TripDTO.class))}),
            @ApiResponse(responseCode = "500", description = "New trip was not updated.")
    })

    @PutMapping("/edit-trip")
    public ResponseEntity<?> editTrip(
            @Parameter(required = true, description = "This is a trip updated in order to add to repo.") @Valid @RequestBody Trip trip
    ) {

        log.info("EDYCJA WYCIECZKI: ");

        try {
            return ResponseEntity.ok(tripService.editTrip(trip));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + exception.getMessage());
        }
    }
}
