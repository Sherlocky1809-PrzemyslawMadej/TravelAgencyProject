package com.example.demo;

import com.example.demo.dto.TripDTO;
import com.example.demo.enums.Currency;
import com.example.demo.enums.TripType;
import com.example.demo.model.*;
import com.example.demo.repository.TripRepository;
import com.example.demo.service.ConverterService;
import com.example.demo.service.TripService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class TripServiceTest {

    @TestConfiguration
    static class TripServiceConfiguration {
        @Bean
        public TripService tripService(TripRepository tripRepository,
                                       ConverterService converterService) {
            return new TripService(tripRepository, converterService);
        }
    }

    @MockBean
    private TripRepository tripRepository;

    @MockBean
    private ConverterService converterService;

    @Autowired
    private TripService tripService;


    @Test
    void when_get_promoted_trips_5_promoted_trips_should_be_returned () throws Exception {

        // given
        Trip trip1 = new Trip();

        trip1.setPromoted(true);
        trip1.setTripType(TripType.AI);
        trip1.setAdultPrice(3000.0);
        trip1.setChildPrice(1500.0);
        trip1.setChildPriceCurrency(Currency.PLN);
        trip1.setAdultPriceCurrency(Currency.PLN);
        trip1.setDateOfDeparture(LocalDate.of(2023,6,10));
        trip1.setDateOfReturn(LocalDate.of(2023,6,17));
        trip1.setDateOfLastUpdate(null);
        trip1.setNumberOfAdultsPlaces((short) 50);
        trip1.setNumberOfChildrenPlaces((short) 25);
        trip1.setNumberOfDays((short) 8);
        trip1.setDeparture(new Departure(3,
                new City(82, "Warszawa", new Country((short) 62, "Polska",
                        new Continent((short) 6, "Europa"))),
                new Airport(82, "WAW", new City(82, "Warszawa", new Country((short) 62, "Polska",
                        new Continent((short) 6, "Europa"))))));
        trip1.setDestination(new Destination(1,
                new City(1, "Sapporo", new Country((short) 1, "Japonia", new Continent((short) 1, "Azja"))),
                new Airport(1, "CTS", new City(1, "Sapporo",
                        new Country((short) 1, "Japonia", new Continent((short) 1, "Azja"))))));
        trip1.setDestinationHotel(new Hotel(1L, "Sapporo Garden Palace",
                (byte) 3, "The hotels location, which is essentially right between Sapporo Station and Odori Station,\n" +
                " is convenient as a base for any kind of trip, such as sightseeing, business, or shopping. It is in walking distance from both the JR line and the subway,\n" +
                " and combines the convenience of being downtown with the added bonus of a quiet location. We hope to make your stay in Sapporo as pleasant as possible (from www.hotelgp-sapporo.com)",
                new City(1, "Sapporo", new Country((short) 1, "Japonia", new Continent((short) 1, "Azja")))));


        Trip trip2 = new Trip();

        trip2.setPromoted(true);
        trip2.setTripType(TripType.FB);
        trip2.setAdultPrice(15000.0);
        trip2.setChildPrice(7500.0);
        trip2.setAdultPriceCurrency(Currency.PLN);
        trip2.setChildPriceCurrency(Currency.PLN);
        trip2.setDateOfDeparture(LocalDate.of(2023,8,25));
        trip2.setDateOfReturn(LocalDate.of(2023,9,1));
        trip2.setDateOfLastUpdate(null);
        trip2.setNumberOfAdultsPlaces((short) 100);
        trip2.setNumberOfChildrenPlaces((short) 50);
        trip2.setNumberOfDays((short) 8);
        trip2.setDeparture(new Departure(2,
                new City(81, "Kraków", new Country((short) 62, "Polska",
                        new Continent((short) 6, "Europa"))),
                new Airport(81, "KRK", new City(81, "Kraków", new Country((short) 62, "Polska",
                        new Continent((short) 6, "Europa"))))));
        trip2.setDestination(new Destination(2,
                new City(2, "Tokio", new Country((short) 1, "Japonia", new Continent((short) 1, "Azja"))),
                new Airport(2, "NRT", new City(2, "Tokio",
                        new Country((short) 1, "Japonia", new Continent((short) 1, "Azja"))))));
        trip2.setDestinationHotel(new Hotel(2L, "JAL City Tokyo Toyosu", (byte) 4,
                "4-gwiazdkowy obiekt Hotel JAL City Tokyo Toyosu znajduje się w miejscowości Tokio.\n" +
                        " Odległość ważnych miejsc od obiektu: Gas Science Museum – niecały kilometr, Urban Dock LaLaport Toyosu – 1,4 km. Do dyspozycji Gości przygotowano takie udogodnienia, jak restauracja, całodobowa recepcja, bankomat oraz bezpłatne WiFi we wszystkich pomieszczeniach.\n" +
                        " Na miejscu zapewniono usługi prasowania, a personel służy pomocą w wymianie walut.(booking.com)",
                new City(2, "Tokio", new Country((short) 1, "Japonia", new Continent((short) 1, "Azja")))));

        Trip trip3 = new Trip();

        trip3.setPromoted(true);
        trip3.setTripType(TripType.HB);
        trip3.setAdultPrice(11000.0);
        trip3.setChildPrice(6500.0);
        trip3.setAdultPriceCurrency(Currency.PLN);
        trip3.setChildPriceCurrency(Currency.PLN);
        trip3.setDateOfDeparture(LocalDate.of(2023,6,5));
        trip3.setDateOfReturn(LocalDate.of(2023,6,19));
        trip3.setDateOfLastUpdate(LocalDateTime.of(2023, 6, 1, 10, 50));
        trip3.setNumberOfAdultsPlaces((short) 100);
        trip3.setNumberOfChildrenPlaces((short) 50);
        trip3.setNumberOfDays((short) 15);
        trip3.setDeparture(new Departure(3,
                new City(83, "Katowice", new Country((short) 62, "Polska",
                        new Continent((short) 6, "Europa"))),
                new Airport(83, "KTW", new City(83, "Katowice", new Country((short) 62, "Polska",
                        new Continent((short) 6, "Europa"))))));
        trip3.setDestination(new Destination(3,
                new City(3, "Denpasar", new Country((short) 2, "Indonezja - Bali",
                        new Continent((short) 1, "Azja"))),
                new Airport(3, "DPS", new City(3, "Denpasar",
                        new Country((short) 2, "Indonezja - Bali", new Continent((short) 1, "Azja"))))));
        trip3.setDestinationHotel(new Hotel(3L, "Ganga Hotel & Apartment", (byte) 3,
                "Ganga Hotel & Apartment features well equipped accommodation with an outdoor pool and a restaurant on site. It provides free WiFi access throughout the property.\n" +
                        "Fitted with a safe, seating area and a flat-screen satellite TV, each modern unit enjoys garden and pool views from its private terrace or balcony. A water dispenser, electric kettle and a minibar are also among the in-room comforts.\n" +
                        " All en suite bathrooms come with a set of free toiletries.(booking.com)",
                new City(3, "Denpasar", new Country((short) 2, "Indonezja - Bali",
                        new Continent((short) 1, "Azja")))));

        Set<Trip> tripSet = new HashSet<>();
        tripSet.add(trip1);
        tripSet.add(trip2);
        tripSet.add(trip3);

        // when
        Set<TripDTO> listOfPromotedTrips = tripService.getTripsPromoted();
        int size = listOfPromotedTrips.size();
        // then
        assertEquals(3, size);
    }

}
