package com.example.demo.dto;

import com.example.demo.enums.Currency;
import com.example.demo.enums.TripType;
import com.example.demo.model.Departure;
import com.example.demo.model.Destination;
import com.example.demo.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TripDTO {

    private Long tripId;
    private DepartureDTO departure;
    private DestinationDTO destination;
    private HotelDTO destinationHotel;
    private LocalDate dateOfDeparture;
    private LocalDate dateOfReturn;
    private Short numberOfDays;
    private TripType tripType;
    private Double adultPrice;
    private Currency adultPriceCurrency;
    private Double childPrice;
    private Currency childPriceCurrency;
    private Boolean isPromoted;
    private Short numberOfAdultsPlaces;
    private Short numberOfChildrenPlaces;

}
