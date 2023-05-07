package com.example.demo.model;

import com.example.demo.pojo.Currency;
import com.example.demo.pojo.TripType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    private Long tripId;
    @ManyToOne
    private City departureCity;
    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private City destinationCity;
    @ManyToOne
    private Airport destinationAirport;
    @ManyToOne
    private Hotel destinationHotel;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate dateOfDeparture;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate dateOfReturn;
    @Min(0)
    private Short numberOfDays;
    private TripType tripType;
    private Double adultPrice;
    private Currency adultPriceCurrency;
    private Double childPrice;
    private Currency childPriceCurrency;
    private Boolean isPromoted;
    @Min(0)
    private Short numberOfAdultsPlaces;
    @Min(0)
    private Short numberOfChildrenPlaces;

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
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
}
