package com.example.demo.controller;

import com.example.demo.dto.TripDTO;
import com.example.demo.model.Trip;
import com.example.demo.service.TripPurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Buy trip", description = "This is a page for purchase trip by user")
@RestController
@Slf4j
public class TripPurchaseController {

    private final TripPurchaseService tripPurchaseService;

    public TripPurchaseController(TripPurchaseService tripPurchaseService) {
        this.tripPurchaseService = tripPurchaseService;
    }


    @Operation(
            summary = "Buy trip",
            description = "This is a endpoint serving trip purchase by User."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TripDTO.class))},
                    description = "New trip was bought"),
            @ApiResponse(responseCode = "500", description = "New trip was not bought.")
    })
    @PostMapping("/buy-trip/{adults}/{children}")
    public ResponseEntity<?> buyTrip(
            @Parameter(required = true, description = "This is a action to buy trip in order to save to purchasing list.")
            @Valid @RequestBody Trip trip,
            @Parameter(required = true, description = "Type number of adults to take part in chosen trip.")
            @PathVariable(name = "adults") Short numberOfAdults,
            @Parameter(required = true, description = "Type number of children to take part in chosen trip.")
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
