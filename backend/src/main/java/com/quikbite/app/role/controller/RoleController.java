package com.quikbite.app.role.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quikbite.app.response.Response;
import com.quikbite.app.role.dtos.RoleDto;
import com.quikbite.app.role.service.RoleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
//@PreAuthorize("hasAuthority('ADMIN')") // internally when request comes, it will check if the user has ADMIN authority before allowing access to any endpoint in this controller
public class RoleController {
    private final RoleService roleService;
    
    @PostMapping
    public ResponseEntity<Response<RoleDto>> createRole( @RequestBody @Valid RoleDto roleDto ){
        return ResponseEntity.ok(roleService.createRole(roleDto));
    }

    @PutMapping
    public ResponseEntity<Response<RoleDto>> updateRole( @RequestBody @Valid RoleDto roleDto ){
        return ResponseEntity.ok(roleService.updateRole(roleDto));
    }

    @GetMapping
    public ResponseEntity<Response<List<RoleDto>>> getAllRoles(  ){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteRole(@PathVariable Long id ){
        return ResponseEntity.ok(roleService.deleteRole(id));
    }


}
