package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

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
