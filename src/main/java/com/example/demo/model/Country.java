package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
    @ManyToOne(cascade = CascadeType.ALL)
    private Continent continent;

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
