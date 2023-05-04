package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    private Integer airportId;
    @Pattern(regexp = "[A-Z].+")
    @NotNull
    private String airportName;
    private City city;

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
