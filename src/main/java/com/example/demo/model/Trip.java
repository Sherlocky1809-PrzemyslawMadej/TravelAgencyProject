package com.example.demo.model;

import com.example.demo.enums.Currency;
import com.example.demo.enums.TripType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Departure departure;
    @ManyToOne(cascade = CascadeType.ALL)
    private Destination destination;
    @ManyToOne(cascade = CascadeType.ALL)
    private Hotel destinationHotel;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfDeparture;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfReturn;
    @Min(0)
    private Short numberOfDays;
    @Enumerated(value = EnumType.STRING)
    private TripType tripType;
    private Double adultPrice;
    @Enumerated(value = EnumType.STRING)
    private Currency adultPriceCurrency;
    private Double childPrice;
    @Enumerated(value = EnumType.STRING)
    private Currency childPriceCurrency;
    private Boolean isPromoted;
    @Min(0)
    private Short numberOfAdultsPlaces;
    @Min(0)
    private Short numberOfChildrenPlaces;
    private LocalDateTime dateOfLastUpdate;


    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setDestinationHotel(Hotel destinationHotel) {
        this.destinationHotel = destinationHotel;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public void setNumberOfDays(Short numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public void setAdultPrice(Double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public void setAdultPriceCurrency(Currency adultPriceCurrency) {
        this.adultPriceCurrency = adultPriceCurrency;
    }

    public void setChildPrice(Double childPrice) {
        this.childPrice = childPrice;
    }

    public void setChildPriceCurrency(Currency childPriceCurrency) {
        this.childPriceCurrency = childPriceCurrency;
    }

    public void setPromoted(Boolean promoted) {
        isPromoted = promoted;
    }

    public void setNumberOfAdultsPlaces(Short numberOfAdultsPlaces) {
        this.numberOfAdultsPlaces = numberOfAdultsPlaces;
    }

    public void setNumberOfChildrenPlaces(Short numberOfChildrenPlaces) {
        this.numberOfChildrenPlaces = numberOfChildrenPlaces;
    }

    public void setDateOfLastUpdate(LocalDateTime dateOfLastUpdate) {
        this.dateOfLastUpdate = dateOfLastUpdate;
    }
}
