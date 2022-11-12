package com.abakli.service.impl;

import com.abakli.entity.Order;
import com.abakli.mapper.OrderMapper;
import com.abakli.mapper.UserMapper;
import com.abakli.repository.OrderRepository;
import com.abakli.service.OrderService;
import com.abakli.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, UserMapper userMapper, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.userMapper = userMapper;
        this.mapper = mapper;
    }


    @Override
    public Order findByUserId(Long id) {

        Optional<Order> order = orderRepository.findByUser_Id(id);

        if (order.isEmpty()) {

//            userService.findById(id)
//
//            new Order(, LocalDate.now(), LocalDate.now().plusDays(1), )
        }
        return null;
    }
}
