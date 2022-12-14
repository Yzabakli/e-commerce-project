package com.abakli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemDTO {

    private Long id;
    private StockItemDTO stockItem;
    private OrderDTO order;
    private Integer quantity;
    private Integer discount;
    private boolean isPayed;
}