package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer destinationId;
    @ManyToOne(cascade = CascadeType.ALL)
    private City destinationCity;
    @ManyToOne(cascade = CascadeType.ALL)
    private Airport destinationAirport;
}
