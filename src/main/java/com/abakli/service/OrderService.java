package com.abakli.service;

import com.abakli.entity.Order;

public interface OrderService {

    Order findByUserId(Long id);
}
