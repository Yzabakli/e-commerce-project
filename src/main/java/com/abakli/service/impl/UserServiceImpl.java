package com.abakli.service.impl;

import com.abakli.dto.UserDTO;
import com.abakli.entity.StockItem;
import com.abakli.entity.User;
import com.abakli.mapper.UserMapper;
import com.abakli.repository.UserRepository;
import com.abakli.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean isAdmin() {

        try {
            return userRepository.findById(2L).orElseThrow().getState() == null;

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

        return mapper.convertToDTO(userRepository.save(convert));
    }

    @Override
    public void delete(Long id) {

        User user = userRepository.findById(id).orElseThrow();
        user.setDeleted(true);
        userRepository.save(user);
    }
}
