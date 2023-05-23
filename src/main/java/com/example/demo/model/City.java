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
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;
    @Pattern(regexp = "[A-Z][\\w ]+")
    @NotNull
    private String cityName;
    @ManyToOne
    private Country country;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
