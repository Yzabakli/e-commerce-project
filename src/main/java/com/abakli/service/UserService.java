package com.abakli.service;

import com.abakli.dto.UserDTO;

import java.util.List;

public interface UserService {

    void save(UserDTO dto);

    List<UserDTO> findAllCustomers();

    UserDTO findById(Long id);

    UserDTO update(UserDTO dto);

    void delete(Long id);

    UserDTO getCurrentUser();
}
