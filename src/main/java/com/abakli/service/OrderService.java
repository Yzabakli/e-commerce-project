package com.abakli.service;

import com.abakli.dto.OrderDTO;

public interface OrderService {

    OrderDTO findByUserId(Long id);

    OrderDTO findById(Long orderId);

    void payOrder(Long orderId);

    void update(OrderDTO dto);
}
