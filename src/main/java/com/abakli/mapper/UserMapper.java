package com.abakli.mapper;

import com.abakli.dto.UserDTO;
import com.abakli.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserDTO convertToDTO(User user) {

        return mapper.map(user, UserDTO.class);
    }

    public User convert(UserDTO dto) {

        return mapper.map(dto, User.class);
    }
}
