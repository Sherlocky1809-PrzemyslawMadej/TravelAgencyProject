package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.enums.TripType;
import com.example.demo.model.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class ConverterService {

    private final ModelMapper modelMapper;

    public ConverterService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ContinentDTO convertContinentToDTO(Continent continent) {
        return modelMapper.map(continent, ContinentDTO.class);
    }

    public CountryDTO convertCountryToDTO(Country country) {

        CountryDTO countryDTO = modelMapper.map(country, CountryDTO.class);
        countryDTO.setContinent(convertContinentToDTO(country.getContinent()));

        return countryDTO;
    }

    public CityDTO convertCityToDTO(City city) {

        CityDTO cityDTO = modelMapper.map(city, CityDTO.class);
        cityDTO.setCountry(convertCountryToDTO(city.getCountry()));

        return cityDTO;
    }

    public AirportDTO convertAirportToDTO(Airport airport) {

        AirportDTO airportDTO = modelMapper.map(airport, AirportDTO.class);
        airportDTO.setCity(convertCityToDTO(airport.getCity()));

        return airportDTO;
    }

    public HotelDTO convertHotelToDTO(Hotel hotel) {

        HotelDTO hotelDTO = modelMapper.map(hotel, HotelDTO.class);
        hotelDTO.setCity(convertCityToDTO(hotel.getCity()));

        return hotelDTO;
    }

    public DepartureDTO convertDepartureToDTO(Departure departure) {

        DepartureDTO departureDTO = modelMapper.map(departure, DepartureDTO.class);
        departureDTO.setDepartureCity(convertCityToDTO(departure.getDepartureCity()));
        departureDTO.setDepartureAirport(convertAirportToDTO(departure.getDepartureAirport()));

        return departureDTO;
    }

    public DestinationDTO convertDestinationToDTO(Destination destination) {

        DestinationDTO destinationDTO = modelMapper.map(destination, DestinationDTO.class);
        destinationDTO.setDestinationCity(convertCityToDTO(destination.getDestinationCity()));
        destinationDTO.setDestinationAirport(convertAirportToDTO(destination.getDestinationAirport()));

        return destinationDTO;

    }

    public TripDTO convertTripToDTO(Trip trip) {

        TripDTO tripDTO = modelMapper.map(trip, TripDTO.class);
        tripDTO.setDeparture(convertDepartureToDTO(trip.getDeparture()));
        tripDTO.setDestination(convertDestinationToDTO(trip.getDestination()));
        tripDTO.setDestinationHotel(convertHotelToDTO(trip.getDestinationHotel()));

        return tripDTO;
    }


    public TripPurchaseDTO convertTripPurchaseToDTO(TripPurchase tripPurchase) {

        TripPurchaseDTO tripPurchaseDTO = modelMapper.map(tripPurchase, TripPurchaseDTO.class);
        tripPurchaseDTO.setPurchasedTrip(convertTripToDTO(tripPurchase.getPurchasedTrip()));

        return tripPurchaseDTO;
    }

}
