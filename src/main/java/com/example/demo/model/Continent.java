package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Continent {

    @Id
    private Byte id;
    private String continentName;

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

}
