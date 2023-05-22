package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airportId;
    @Pattern(regexp = "[A-Z].+")
    @NotNull
    private String airportName;
    @ManyToOne
    private City city;

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
