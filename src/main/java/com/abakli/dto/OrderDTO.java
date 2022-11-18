package com.abakli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private UserDTO user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shipDate;
    private boolean isPayed;
}