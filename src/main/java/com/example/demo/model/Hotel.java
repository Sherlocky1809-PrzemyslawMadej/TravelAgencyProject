package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


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
    @ManyToOne(cascade = CascadeType.ALL)
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
