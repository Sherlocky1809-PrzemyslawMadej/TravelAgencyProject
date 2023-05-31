package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.Trip;
import com.example.demo.repository.TripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith((SpringExtension.class))
class TripServiceTest {

    @TestConfiguration
    static class TripServiceConfiguration {
        @Bean
        public TripService tripService(TripRepository tripRepository, ConverterService converterService) {
            return new TripService(tripRepository, converterService);
        }
    }

    @MockBean
    TripRepository tripRepository;

    @MockBean
    ConverterService converterService;

    @Autowired
    TripService tripService;


    @Test
    void should_get_trips_by_given_continent_name_and_country_name() {
        // given

        ContinentDTO continentDTO = new ContinentDTO("Azja");
        ContinentDTO departureContinentDTO = new ContinentDTO("Europa");

        CountryDTO countryDTO = new CountryDTO("Japonia", continentDTO);
        CountryDTO departureCountryDTO = new CountryDTO("Polska", departureContinentDTO);

        CityDTO cityDTO = new CityDTO("Sapporo", countryDTO);
        CityDTO departureCityDTO = new CityDTO("Warszawa", departureCountryDTO);

        AirportDTO airportDTO = new AirportDTO("CTS", cityDTO);
        AirportDTO departureAirportDTO = new AirportDTO("WAW", departureCityDTO);

        DepartureDTO departureDTO = new DepartureDTO(departureCityDTO, departureAirportDTO);
        DestinationDTO destinationDTO = new DestinationDTO(cityDTO, airportDTO);

        HotelDTO hotelDTO = new HotelDTO("Sapporo Garden Palace", (byte) 3, "The hotels location, which is essentially right between Sapporo Station and Odori Station,\n" +
                " is convenient as a base for any kind of trip, such as sightseeing, business, or shopping. It is in walking distance from both the JR line and the subway,\n" +
                " and combines the convenience of being downtown with the added bonus of a quiet location. We hope to make your stay in Sapporo as pleasant as possible (from www.hotelgp-sapporo.com)", cityDTO);

        List<TripDTO> tripDTOList = tripRepository.findAll().stream()
                .filter(trip -> (DAYS.between(LocalDate.now(), trip.getDateOfDeparture()) < 31) && (DAYS.between(LocalDate.now(), trip.getDateOfDeparture()) > 0))
                .sorted(Comparator.comparing(Trip::getDateOfDeparture))
                .map(converterService::convertTripToDTO)
                .toList();

        String continentName = "Azja";
        String countryName = "Japonia";

        Mockito.when(tripDTOList.stream()
                        .filter(trip -> trip.getDestination().getDestinationCity().getCountry()
                                .getContinent().getContinentName().toLowerCase().contains(continentName.toLowerCase()))
                        .filter(trip -> trip.getDestination().getDestinationCity()
                                .getCountry().getCountryName().toLowerCase().contains(countryName.toLowerCase()))
                        .limit(5)
                        .collect(Collectors.toList()))
                .thenReturn(List.of(
                        new TripDTO(1L, new DepartureDTO(new CityDTO("Sapporo", new CountryDTO("Japonia", new ContinentDTO("Azja"))), new AirportDTO("CTS", new CityDTO("Sapporo", new CountryDTO("Japonia", new ContinentDTO("Azja"))))))))
        // when

        // then
    }

}