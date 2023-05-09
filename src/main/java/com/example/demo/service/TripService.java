package com.example.demo.service;

import com.example.demo.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TripService {

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;
    private final AirportRepository airportRepository;

    private final DestinationRepository destinationRepository;
    private final DepartureRepository departureRepository;

    public TripService(HotelRepository hotelRepository, CityRepository cityRepository, AirportRepository airportRepository,
                       DestinationRepository destinationRepository, DepartureRepository departureRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
        this.airportRepository = airportRepository;
        this.destinationRepository = destinationRepository;
        this.departureRepository = departureRepository;
    }


}
