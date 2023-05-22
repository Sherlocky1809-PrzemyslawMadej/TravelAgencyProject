package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TripPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;
    @ManyToOne
    private Trip purchasedTrip;
    @Min(1)
    private Short numberOfAdults;
    @Min(0)
    private Short numberOfChildren;
    private Double totalPrice;

    public void setPurchasedTrip(Trip purchasedTrip) {
        this.purchasedTrip = purchasedTrip;
    }

    public void setNumberOfAdults(Short numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public void setNumberOfChildren(Short numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
