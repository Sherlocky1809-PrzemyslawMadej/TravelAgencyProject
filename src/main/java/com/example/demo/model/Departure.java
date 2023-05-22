package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Departure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departureId;
    @ManyToOne
    private City departureCity;
    @ManyToOne
    private Airport departureAirport;
}
