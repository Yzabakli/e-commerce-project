package com.abakli.service.impl;

import com.abakli.dto.OrderDTO;
import com.abakli.dto.UserDTO;
import com.abakli.entity.User;
import com.abakli.mapper.UserMapper;
import com.abakli.repository.UserRepository;
import com.abakli.service.OrderService;
import com.abakli.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrderService orderService;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, @Lazy OrderService orderService, UserMapper mapper) {
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @Override
    public boolean isAdmin() {

        try {
            return userRepository.findById(1L).orElseThrow().getState() == null; // todo: hardcoded

        } catch (NoSuchElementException e) {

            return true;
        }
    }

    @Override
    public void save(UserDTO dto) {
        userRepository.save(mapper.convert(dto));
    }

    @Override
    public List<UserDTO> findAllCustomers() {
        return userRepository.findByStreetNotNull().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return mapper.convertToDTO(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserDTO update(UserDTO dto) {

        User convert = mapper.convert(dto);

        convert.setId(dto.getId());

        try {

            OrderDTO orderDTO = orderService.findByUserId(convert.getId());

            orderDTO.setStreet(convert.getStreet());
            orderDTO.setCity(convert.getCity());
            orderDTO.setState(convert.getState());
            orderDTO.setZipCode(convert.getZipCode());
            orderDTO.setShipDate(orderDTO.getShipDate().plusDays(1));

            orderService.update(orderDTO);

        } catch (NoSuchElementException ignored) {


        }

        return mapper.convertToDTO(userRepository.save(convert));
    }

    @Override
    public void delete(Long id) {

        User user = userRepository.findById(id).orElseThrow();
        user.setDeleted(true);
        userRepository.save(user);
    }
}
