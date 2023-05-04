package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private Short countryId;
    @Pattern(regexp = "[A-Z].+")
    @NotNull
    private String countryName;
    @ManyToOne
    private Continent continent;

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
