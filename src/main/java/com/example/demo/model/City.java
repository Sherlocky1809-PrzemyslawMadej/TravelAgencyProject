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
public class City {

    @Id
    private Integer cityId;
    @Pattern(regexp = "[A-Z].+")
    @NotNull
    private String cityName;
    @ManyToOne
    private Country country;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
