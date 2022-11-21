package com.abakli.service.impl;

import com.abakli.dto.RoleDTO;
import com.abakli.entity.Role;
import com.abakli.mapper.RoleMapper;
import com.abakli.repository.RoleRepository;
import com.abakli.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> listAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream().map(roleMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {


        return roleMapper.convertToDto(roleRepository.findById(id).orElseThrow());
    }
}
