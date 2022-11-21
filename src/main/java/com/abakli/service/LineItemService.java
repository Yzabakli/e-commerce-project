package com.abakli.service;

import com.abakli.dto.LineItemDTO;

import java.util.List;

public interface LineItemService {

    void findByOrder_IdAndStockItem_Id(Long orderId, Long itemId);

    List<LineItemDTO> readAllByCurrentUser();

    void removeById(Long itemId);

    void delete(Long id);

    List<LineItemDTO> findByOrder_Id(Long id);
}
