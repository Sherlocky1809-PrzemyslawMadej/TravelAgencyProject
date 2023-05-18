package com.example.demo.service;

import com.example.demo.dto.TripDTO;
import com.example.demo.enums.Currency;
import com.example.demo.enums.TripType;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MINUTES;

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

    public Set<TripDTO> getTripsPromoted() {
        return tripRepository.findAllPromoted().stream()
                .map(converterService::convertTripToDTO)
                .collect(Collectors.toSet());
    }


    public List<TripDTO> getUpcomingTripsByContinentNameOrCountryName(String continentName, String countryName) {

        List<TripDTO> listOfUpcomingTrips = tripRepository.findAll().stream()
                .filter(trip -> DAYS.between(LocalDate.now(), trip.getDateOfDeparture()) < 31)
                .sorted(Comparator.comparing(Trip::getDateOfDeparture))
                .map(converterService::convertTripToDTO)
                .toList();


        if(continentName != null && countryName != null) {
            return listOfUpcomingTrips.stream()
                    .filter(trip -> trip.getDestination().getDestinationCity().getCountry()
                            .getContinent().getContinentName().toLowerCase().contains(continentName.toLowerCase()))
                    .filter(trip -> trip.getDestination().getDestinationCity()
                            .getCountry().getCountryName().toLowerCase().contains(countryName.toLowerCase()))
                    .limit(5)
                    .collect(Collectors.toList());
        }

        if(countryName != null) {
            return listOfUpcomingTrips.stream()
                    .filter(trip -> trip.getDestination().getDestinationCity()
                            .getCountry().getCountryName().toLowerCase().contains(countryName.toLowerCase()))
                    .limit(5)
                    .collect(Collectors.toList());
        }

        if(continentName != null) {
            return listOfUpcomingTrips.stream()
                    .filter(trip -> trip.getDestination().getDestinationCity().getCountry()
                            .getContinent().getContinentName().toLowerCase().contains(continentName.toLowerCase()))
                    .limit(5)
                    .collect(Collectors.toList());
        }

        return listOfUpcomingTrips.stream()
                .limit(5).collect(Collectors.toList());
    }


    public List<TripDTO> getTripsLastPurchased() {
        return tripRepository.findAll().stream()
                .filter(trip -> trip.getDateOfLastUpdate() != null)
                .filter(trip -> MINUTES.between(LocalDateTime.now(), trip.getDateOfLastUpdate()) < 10)
                .map(converterService::convertTripToDTO)
                .limit(5)
                .collect(Collectors.toList());
    }


    public List<TripDTO> getTripsByParameters(String cityOfDeparture, String airportOfDeparture, String cityOfDestination,
                                              String airportOfDestination, LocalDate dateOfDeparture, LocalDate dateOfDestination,
                                              TripType typeOfTrip, Byte hotelNumberOfStars, Short numberOfDays) {

        return tripRepository.findAllByGivenParameters(
                cityOfDeparture != null ? cityOfDeparture.toLowerCase() : null,
                airportOfDeparture != null ? airportOfDeparture.toLowerCase() : null,
                cityOfDestination != null ? cityOfDestination.toLowerCase() : null,
                airportOfDestination != null ? airportOfDestination.toLowerCase() : null,
                dateOfDeparture,
                dateOfDestination,
                typeOfTrip,
                hotelNumberOfStars,
                numberOfDays
        ).stream().map(converterService::convertTripToDTO)
                .collect(Collectors.toList());
    }

    /**
     * All functionalities below are configurated by Admin.
     */

    public TripDTO addTrip(Trip trip) {

        tripRepository.findByTripId(trip.getTripId())
                .ifPresent(o -> {
                    throw new IllegalArgumentException("Trip already exists!");
                });

        return converterService.convertTripToDTO(trip);
    }

    public TripDTO editTrip(Trip trip) {

       Departure departure = trip.getDeparture();
       Destination destination = trip.getDestination();
       Hotel hotel = trip.getDestinationHotel();
       LocalDate dateOfDeparture = trip.getDateOfDeparture();
       LocalDate dateOfReturn = trip.getDateOfReturn();
       Short numberOfDays = trip.getNumberOfDays();
       TripType typeOfTrip = trip.getTripType();
       Double adultPrice = trip.getAdultPrice();
       Currency adultPriceCurrency = trip.getAdultPriceCurrency();
       Double childPrice = trip.getChildPrice();
       Currency childPriceCurrency = trip.getChildPriceCurrency();
       Boolean isPromoted = trip.getIsPromoted();
       Short numberOfAdultsPlaces = trip.getNumberOfAdultsPlaces();
       Short numberOfChildrenPlaces = trip.getNumberOfChildrenPlaces();

       Trip tripToEdit = tripRepository.findByTripId(trip.getTripId())
               .map(tr -> {
                   tr.setDeparture(departure != null ? departure : tr.getDeparture());
                   tr.setDestination(destination != null ? destination : tr.getDestination());
                   tr.setDestinationHotel(hotel != null ? hotel : tr.getDestinationHotel());
                   tr.setDateOfDeparture(dateOfDeparture != null ? dateOfDeparture : tr.getDateOfDeparture());
                   tr.setDateOfReturn(dateOfReturn != null ? dateOfReturn : tr.getDateOfReturn());
                   tr.setNumberOfDays(numberOfDays != null ? numberOfDays : tr.getNumberOfDays());
                   tr.setTripType(typeOfTrip != null ? typeOfTrip : tr.getTripType());
                   tr.setAdultPrice(adultPrice != null ? adultPrice : tr.getAdultPrice());
                   tr.setAdultPriceCurrency(adultPriceCurrency != null ? adultPriceCurrency : tr.getAdultPriceCurrency());
                   tr.setChildPrice(childPrice != null ? childPrice : tr.getChildPrice());
                   tr.setChildPriceCurrency(childPriceCurrency != null ? childPriceCurrency : tr.getChildPriceCurrency());
                   tr.setPromoted(isPromoted != null ? isPromoted : tr.getIsPromoted());
                   tr.setNumberOfAdultsPlaces(numberOfAdultsPlaces != null ? numberOfAdultsPlaces : tr.getNumberOfAdultsPlaces());
                   tr.setNumberOfChildrenPlaces(numberOfChildrenPlaces != null ? numberOfChildrenPlaces : tr.getNumberOfChildrenPlaces());

                   return tr;

               })
               .orElseThrow(() -> new ResourceNotFoundException("No trip to update found!"));

       Trip tripUpdated = tripRepository.save(tripToEdit);

       return converterService.convertTripToDTO(tripUpdated);
    }


}
