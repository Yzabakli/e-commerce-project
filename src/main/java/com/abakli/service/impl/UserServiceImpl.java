package com.abakli.service.impl;

import com.abakli.dto.OrderDTO;
import com.abakli.dto.UserDTO;
import com.abakli.entity.Role;
import com.abakli.entity.User;
import com.abakli.mapper.UserMapper;
import com.abakli.repository.UserRepository;
import com.abakli.service.OrderService;
import com.abakli.service.RoleService;
import com.abakli.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OrderService orderService;
    private final RoleService roleService;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy OrderService orderService, RoleService roleService, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.roleService = roleService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(UserDTO dto) {


        if (dto.getCity() == null) {

            dto.setRole(roleService.findById(1L));

        } else dto.setRole(roleService.findById(2L));

        User user = mapper.convert(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findAllCustomers() {
        return userRepository.findByRole_Id(2L).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return mapper.convertToDTO(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserDTO update(UserDTO dto) {

        User convert = mapper.convert(dto);

        Role role = userRepository.findById(dto.getId()).orElseThrow().getRole();

        convert.setId(dto.getId());

        convert.setPassword(passwordEncoder.encode(dto.getPassword()));

        convert.setRole(role);

        if (orderService.existsByUser_Id(convert.getId())) {

            OrderDTO orderDTO = orderService.findByCurrentUser();

            orderDTO.setShipDate(orderDTO.getShipDate().plusDays(1));

            orderService.update(orderDTO);
        }

        return mapper.convertToDTO(userRepository.save(convert));
    }

    @Override
    public void delete(Long id) {

        User user = userRepository.findById(id).orElseThrow();


        if (orderService.existsByUser_Id(id)) {

            OrderDTO order = orderService.findByUserId(id).orElseThrow();

            orderService.delete(order.getId());

            orderService.update(order);
        }

        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public UserDTO getCurrentUser() {
        return mapper.convertToDTO(userRepository.findByPhoneNumber(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow());
    }
}
