package com.abakli.service.impl;

import com.abakli.dto.OrderDTO;
import com.abakli.entity.Order;
import com.abakli.entity.User;
import com.abakli.mapper.OrderMapper;
import com.abakli.mapper.UserMapper;
import com.abakli.repository.OrderRepository;
import com.abakli.service.OrderService;
import com.abakli.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, @Lazy UserService userService, UserMapper userMapper, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.userMapper = userMapper;
        this.mapper = mapper;
    }


    @Override
    public OrderDTO findByUserId(Long id) {

        Optional<Order> order = orderRepository.findByUser_Id(id);

        if (order.isEmpty()) {

            User user = userMapper.convert(userService.findById(id));

            order = Optional.of(new Order(user, LocalDate.now(), LocalDate.now()
                    .plusDays(1), user.getStreet(), user.getCity(), user.getState(), user.getZipCode(), false));
        }
        return mapper.convertToDTO(orderRepository.save(order.orElseThrow()));
    }

    @Override
    public OrderDTO findById(Long orderId) {
        return mapper.convertToDTO(orderRepository.findById(orderId).orElseThrow());
    }

    @Override
    public void payOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setPayed(true);
        order.setDeleted(true);
        orderRepository.save(order);
    }

    @Override
    public void update(OrderDTO dto) {

        Order convert = mapper.convert(dto);

        convert.setId(dto.getId());

        orderRepository.save(convert);
    }
}
