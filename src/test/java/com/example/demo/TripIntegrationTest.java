package com.example.demo;

import com.example.demo.enums.Currency;
import com.example.demo.enums.TripType;
import com.example.demo.model.*;
import com.example.demo.repository.TripRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TravelAgencyApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TripIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TripRepository tripRepository;

    @LocalServerPort
    private int serverPort;

    Trip trip1;
    Trip trip2;
    Trip trip3;

    @BeforeAll
    public void setupTrips() {

        trip1 = new Trip();

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


        trip2 = new Trip();

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

        trip3 = new Trip();

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

    }

    @AfterAll
    public void teardown() {
        trip1 = null;
        trip2 = null;
        trip3 = null;
    }

    @Test
    void when_get_promoted_trips_5_promoted_trips_should_be_returned () throws Exception {
        // given

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/trips-promoted")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
