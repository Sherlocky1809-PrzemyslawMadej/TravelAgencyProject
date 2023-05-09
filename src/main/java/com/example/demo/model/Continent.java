package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;
    @Pattern(regexp = "[A-Z][\\w ]+")
    private String continentName;

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

}
