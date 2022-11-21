package com.abakli.service;

import com.abakli.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<OrderDTO> findByUserId(Long id);

    OrderDTO findByCurrentUser();

    OrderDTO findById(Long orderId);

    void payOrder(Long orderId);

    void update(OrderDTO dto);

    boolean existsByUser_Id(Long id);

    List<OrderDTO> readAllPayed();

    void delete(Long id);
}
