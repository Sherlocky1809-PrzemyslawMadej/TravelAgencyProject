package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ClientDTO {

    private Long clientId;
    private String firstName;
    private String lastName;
    private Short age;
    private String email;
    private String pesel;
    private String numberOfIdCard;

}
