package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    @Pattern(regexp = "[A-Z].+")
    @NotNull
    private String hotelName;
    @Min(1)
    @Max(5)
    private Byte numberOfStars;
    @Column(length = 10000)
    @Length(max = 10000)
    private String description;
    @ManyToOne
    private City city;

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setNumberOfStars(Byte numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
