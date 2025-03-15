package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.PermissionRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.PermissionResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Permission;
import com.rodrigvf.copiou_papelaria_api.mapper.PermissionMapper;
import com.rodrigvf.copiou_papelaria_api.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping()
    public ResponseEntity<List<PermissionResponse>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.findAll()
                .stream()
                .map(PermissionMapper::toPermissionResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponse> getPermissionById(@PathVariable Long id) {
        return permissionService.findById(id)
                .map(permission -> ResponseEntity.ok(PermissionMapper.toPermissionResponse(permission)))
                .orElse(ResponseEntity.notFound().build());
    }

}
