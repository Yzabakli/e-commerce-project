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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final LineItemServiceImpl lineItemService;
    private final UserMapper userMapper;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, @Lazy UserService userService, @Lazy LineItemServiceImpl lineItemService, UserMapper userMapper, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.lineItemService = lineItemService;
        this.userMapper = userMapper;
        this.mapper = mapper;
    }


    @Override
    public OrderDTO findByCurrentUser() {

        Optional<Order> order = orderRepository.findByUser_IdAndIsPayedFalse(userService.getCurrentUser().getId());

        if (order.isEmpty()) {

            User user = userMapper.convert(userService.getCurrentUser());

            order = Optional.of(new Order(user, LocalDate.now(), LocalDate.now().plusDays(1), false));
        }
        return mapper.convertToDTO(orderRepository.save(order.orElseThrow()));
    }

    @Override
    public Optional<OrderDTO> findByUserId(Long id) {

        return Optional.of(mapper.convertToDTO(orderRepository.findByUser_Id(id).orElseThrow()));
    }

    @Override
    public OrderDTO findById(Long orderId) {
        return mapper.convertToDTO(orderRepository.findById(orderId).orElseThrow());
    }

    @Override
    public void payOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow();

        lineItemService.findByOrder_Id(orderId).forEach(dto -> lineItemService.pay(dto.getId()));

        order.setPayed(true);

        orderRepository.save(order);
    }

    @Override
    public void update(OrderDTO dto) {

        Order convert = mapper.convert(dto);

        convert.setId(dto.getId());

        orderRepository.save(convert);
    }

    @Override
    public boolean existsByUser_Id(Long id) {
        return orderRepository.existsByUser_Id(id);
    }

    @Override
    public List<OrderDTO> readAllPayed() {
        return orderRepository.findByUser_IdAndIsPayedTrue(userService.getCurrentUser().getId())
                .stream()
                .map(mapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        Order order = orderRepository.findById(id).orElseThrow();

        lineItemService.findByOrder_Id(id).forEach(dto -> lineItemService.delete(dto.getId()));

        order.setIsDeleted(true);

        orderRepository.save(order);
    }
}
