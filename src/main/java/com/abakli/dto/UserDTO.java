package com.abakli.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;

    public UserDTO(String name) {
        this.name = name;
    }
}