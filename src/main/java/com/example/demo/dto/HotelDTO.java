package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class HotelDTO {

    private String hotelName;
    private Byte numberOfStars;
    private String description;
    private CityDTO city;

}
