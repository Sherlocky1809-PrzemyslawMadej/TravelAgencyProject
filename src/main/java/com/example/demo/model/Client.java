package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.PESEL;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @Pattern(regexp = "[A-Z][a-z]+")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]+")
    private String lastName;
    @Min(3)
    private Short age;
    @Email
    private String email;
    @PESEL
    private String pesel;
    @Pattern(regexp = "[A-Z]{3}[0-9]{6}")
    private String numberOfIdCard;
}
