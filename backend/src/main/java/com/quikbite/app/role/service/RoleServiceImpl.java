package com.quikbite.app.role.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.quikbite.app.exception.BadRequestException;
import com.quikbite.app.exception.NotFoundException;
import com.quikbite.app.response.Response;
import com.quikbite.app.role.dtos.RoleDto;
import com.quikbite.app.role.model.Role;
import com.quikbite.app.role.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper; // here we need to it because to map entity to dto and vice versa

    @Override
    public Response<RoleDto> createRole(RoleDto roleDto) {
        if (!StringUtils.hasText(roleDto.getName())) {
            throw new BadRequestException("Role name cannot be blank");
        }
        roleRepository.findByName(roleDto.getName()).ifPresent(r -> {
            throw new BadRequestException("Role with name already exists");
        });

        Role role = modelMapper.map(roleDto, Role.class);
        Role savedRole = roleRepository.save(role);

        return Response.<RoleDto>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role created successfully")
                .data(modelMapper.map(savedRole, RoleDto.class))
                .build();
    }

    @Override
    public Response<RoleDto> updateRole(RoleDto roleDto) {
        Role existRole = roleRepository.findById(roleDto.getId())
                .orElseThrow(() -> new NotFoundException("Role not found"));

        if (!StringUtils.hasText(roleDto.getName())) {
            throw new BadRequestException("Role name cannot be blank");
        }

        roleRepository.findByName(roleDto.getName()).ifPresent(r -> {
            if (!r.getId().equals(existRole.getId())) {
                throw new BadRequestException("Role with name already exists");
            }
        });

        existRole.setName(roleDto.getName());
        Role updatedRole = roleRepository.save(existRole);
        return Response.<RoleDto>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role updated successfully")
                .data(modelMapper.map(updatedRole, RoleDto.class))
                .build();
    }

    @Override
    public Response<List<RoleDto>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = roles.stream()
                .map( role -> modelMapper.map(role, RoleDto.class) )
                .toList();
        return Response.<List<RoleDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Roles retrieved successfully")
                .data(roleDtos)
                .build();
    }

    @Override
    public Response<?> deleteRole(Long roleId) {
        Role existRole = roleRepository.findById(roleId)
                .orElseThrow( () -> new NotFoundException("Role not found") );
        roleRepository.delete(existRole);
        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role deleted successfully")
                .build();
    }

}
