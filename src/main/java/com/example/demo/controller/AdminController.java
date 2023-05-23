package com.example.demo.controller;

import com.example.demo.model.Trip;
import com.example.demo.service.TripService;
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


    @PostMapping("/add-trip")
    public ResponseEntity<?> addTripToRepo(@Valid @RequestBody Trip trip) {

    log.info("DODANIE NOWEJ WYCIECZKI: ");

        try {
            return ResponseEntity.ok(tripService.addTrip(trip));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + exception.getMessage());
        }
    }

    @PutMapping("/edit-trip")
    public ResponseEntity<?> editTripById(@Valid @RequestBody Trip trip) {

        log.info("EDYCJA WYCIECZKI: ");

        try {
            return ResponseEntity.ok(tripService.editTrip(trip));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + exception.getMessage());
        }
    }
}
