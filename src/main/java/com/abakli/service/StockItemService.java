package com.abakli.service;

import com.abakli.dto.StockItemDTO;

import java.util.List;

public interface StockItemService {
    List<StockItemDTO> readAll();

    void save(StockItemDTO dto);

    StockItemDTO findById(Long itemId);

    void update(StockItemDTO dto);

    void delete(Long itemId);
}
