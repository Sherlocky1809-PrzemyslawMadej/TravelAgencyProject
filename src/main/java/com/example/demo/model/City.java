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
