package com.abakli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shipDate;
    private String street;
    private String city;
    private String state;
    private String zipCode;
}