package com.example.demo.controller;

import com.example.demo.model.Trip;
import com.example.demo.service.TripPurchaseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class TripPurchaseController {

    private final TripPurchaseService tripPurchaseService;

    public TripPurchaseController(TripPurchaseService tripPurchaseService) {
        this.tripPurchaseService = tripPurchaseService;
    }

    @PostMapping("/buy-trip/{adults}/{children}")
    public ResponseEntity<?> buyTrip(
            @Valid @RequestBody Trip trip,
            @PathVariable(name = "adults") Short numberOfAdults,
            @PathVariable(name = "children") Short numberOfChildren
                    ) {

        try {
            return ResponseEntity.ok(tripPurchaseService.addTripPurchaseDTO(trip, numberOfAdults, numberOfChildren));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + e.getMessage());
        }
    }
}
