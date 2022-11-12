package com.abakli.mapper;

import com.abakli.dto.OrderDTO;
import com.abakli.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ModelMapper mapper;

    public OrderMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public OrderDTO convertToDTO(Order order) {

        return mapper.map(order, OrderDTO.class);
    }

    public Order convert(OrderDTO dto) {

        return mapper.map(dto, Order.class);
    }
}
