package com.abakli.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockItemDTO {
    private Long id;
    private String description;
    private BigDecimal price;
}