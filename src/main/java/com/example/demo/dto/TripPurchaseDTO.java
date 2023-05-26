package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TripPurchaseDTO {

    private Long purchaseId;
    private TripDTO purchasedTrip;
    private Short numberOfAdults;
    private Short numberOfChildren;
    private Double totalPrice;
}
