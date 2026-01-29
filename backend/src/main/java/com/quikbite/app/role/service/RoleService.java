package com.quikbite.app.role.service;

import java.util.List;

import com.quikbite.app.response.Response;
import com.quikbite.app.role.dtos.RoleDto;

public interface RoleService {
    Response<RoleDto> createRole(RoleDto roleDto);
    Response<RoleDto> updateRole(RoleDto roleDto);
    Response<List<RoleDto>> getAllRoles();
    Response<?> deleteRole(Long roleId);
}
