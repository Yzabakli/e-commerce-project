package com.abakli.mapper;

import com.abakli.dto.RoleDTO;
import com.abakli.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Role convertToEntity(RoleDTO dto) {
        return modelMapper.map(dto, Role.class);
    }

    public RoleDTO convertToDto(Role entity) {
        return modelMapper.map(entity, RoleDTO.class);
    }

}
