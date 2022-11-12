package com.abakli.service;

import com.abakli.dto.LineItemDTO;

import java.util.List;

public interface LineItemService {

    void findByOrder_IdAndStockItem_Id(Long orderId, Long itemId);

    List<LineItemDTO> readAllById(Long id);

    void removeById(Long itemId);
}
