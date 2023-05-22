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
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short countryId;
    @Pattern(regexp = "[A-Z][\\w ]+")
    @NotNull
    private String countryName;
    @ManyToOne
    private Continent continent;

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
