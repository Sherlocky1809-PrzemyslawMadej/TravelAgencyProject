package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Departure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departureId;
    @ManyToOne(cascade = CascadeType.ALL)
    private City departureCity;
    @ManyToOne(cascade = CascadeType.ALL)
    private Airport departureAirport;
}
