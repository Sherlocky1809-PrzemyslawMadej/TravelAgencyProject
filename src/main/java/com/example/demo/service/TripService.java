package com.example.demo.service;

import com.example.demo.dto.TripDTO;
import com.example.demo.model.Continent;
import com.example.demo.model.Country;
import com.example.demo.model.Trip;
import com.example.demo.repository.*;
import com.example.demo.service.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Slf4j
public class TripService {

    private final ContinentRepository continentRepository;
    private final CountryRepository countryRepository;

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;
    private final AirportRepository airportRepository;

    private final DestinationRepository destinationRepository;
    private final DepartureRepository departureRepository;

    private final TripRepository tripRepository;

    private final ConverterService converterService;

    public TripService(ContinentRepository continentRepository, CountryRepository countryRepository,
                       HotelRepository hotelRepository, CityRepository cityRepository, AirportRepository airportRepository,
                       DestinationRepository destinationRepository, DepartureRepository departureRepository,
                       TripRepository tripRepository, ConverterService converterService) {
        this.continentRepository = continentRepository;
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
        this.airportRepository = airportRepository;
        this.destinationRepository = destinationRepository;
        this.departureRepository = departureRepository;
        this.tripRepository = tripRepository;
        this.converterService = converterService;
    }

    public List<TripDTO> getTripsPromoted() {
        return tripRepository.findAllPromoted().stream()
                .map(converterService::convertTripToDTO)
                .collect(Collectors.toList());
    }


    public List<TripDTO> getUpcomingTripsByContinentNameOrCountryName(String continentName, String countryName) {

        List<TripDTO> listOfUpcomingTrips = tripRepository.findAll().stream()
                .filter(trip -> DAYS.between(LocalDate.now(), trip.getDateOfDeparture()) < 31)
                .map(converterService::convertTripToDTO)
                .limit(5).toList();

        if(continentName != null) {
            return listOfUpcomingTrips.stream()
                    .filter(trip -> trip.getDeparture().getDepartureCity().getCountry()
                            .getContinent().getContinentName().equals(continentName))
                    .collect(Collectors.toList());

//            log.info("The name of continent is not null - list of trips is filtered by given continent name!");
        }
        if(countryName != null) {
            return listOfUpcomingTrips.stream()
                    .filter(trip -> trip.getDeparture().getDepartureCity().getCountry().getCountryName().equals(countryName))
                    .collect(Collectors.toList());
        }
        return listOfUpcomingTrips;
    }

    public List<TripDTO> getTripsLastPurchased() {
        return null;
    }


}
