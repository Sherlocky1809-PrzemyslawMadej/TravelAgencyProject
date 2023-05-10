package com.example.demo.service;

import com.example.demo.dto.TripPurchaseDTO;
import com.example.demo.model.Trip;
import com.example.demo.model.TripPurchase;
import com.example.demo.repository.TripPurchaseRepository;
import com.example.demo.repository.TripRepository;
import com.example.demo.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TripPurchaseService {

    private final TripPurchaseRepository tripPurchaseRepository;
    private final TripRepository tripRepository;

    private final ConverterService converterService;

    public TripPurchaseService(TripPurchaseRepository tripPurchaseRepository, TripRepository tripRepository,
                               ConverterService converterService) {
        this.tripPurchaseRepository = tripPurchaseRepository;
        this.tripRepository = tripRepository;
        this.converterService = converterService;
    }


    public TripPurchaseDTO addTripPurchaseDTO(Trip tripToPurchase, Short numberOfAdults, Short numberOfChildren) {

        TripPurchase tripPurchase = addTripPurchase(tripToPurchase, numberOfAdults, numberOfChildren);

        return converterService.convertTripPurchaseToDTO(tripPurchase);
    }

    private TripPurchase addTripPurchase(Trip tripToPurchase, Short numberOfAdults, Short numberOfChildren) {

        TripPurchase tripOrder = new TripPurchase();

        Trip trip = tripRepository.findByTripId(tripToPurchase.getTripId())
                        .orElseThrow(() -> new ResourceNotFoundException("The trip by given ID is not exists!"));

        tripOrder.setPurchasedTrip(trip);
        tripOrder.setNumberOfAdults(numberOfAdults);
        tripOrder.setNumberOfChildren(numberOfChildren);

        if ((trip.getNumberOfAdultsPlaces() >= numberOfAdults)
                && (trip.getNumberOfChildrenPlaces() >= numberOfChildren)) {

            tripOrder.setTotalPrice((trip.getAdultPrice() * numberOfAdults)
                    + (trip.getChildPrice() * numberOfChildren));

            trip.setDateOfLastUpdate(LocalDateTime.now());
            trip.setNumberOfAdultsPlaces((short) (trip.getNumberOfAdultsPlaces() - numberOfAdults));
            trip.setNumberOfChildrenPlaces((short) (trip.getNumberOfChildrenPlaces() - numberOfChildren));

            return tripPurchaseRepository.save(tripOrder);
        } else {
            throw new ResourceNotFoundException("The purchase was not completed! The chosen trip don't have enough free places.");
        }
    }

}
