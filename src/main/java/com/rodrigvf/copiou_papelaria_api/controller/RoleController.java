package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.RoleRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.RoleResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Role;
import com.rodrigvf.copiou_papelaria_api.mapper.RoleMapper;
import com.rodrigvf.copiou_papelaria_api.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping()
    public ResponseEntity<List<RoleResponse>> getAll() {
        return ResponseEntity.ok(roleService.findAll()
                .stream()
                .map(RoleMapper::toRoleResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getById(@PathVariable Long id) {
        return roleService.findById(id)
                .map(role -> ResponseEntity.ok(RoleMapper.toRoleResponse(role)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('role:create')")
    public ResponseEntity<RoleResponse> save(@Valid @RequestBody RoleRequest request) {
        Role newRole = RoleMapper.toRole(request);
        Role savedRole = roleService.save(newRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(RoleMapper.toRoleResponse(savedRole));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product:update')")
    public ResponseEntity<RoleResponse> update(@PathVariable Long id, @Valid @RequestBody RoleRequest request) {
        return roleService.update(id, RoleMapper.toRole(request))
                .map(role -> ResponseEntity.ok(RoleMapper.toRoleResponse(role)))
                .orElse(ResponseEntity.notFound().build());
    }

    //TODO: PATCH permissions

}
