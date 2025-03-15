package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Permission;
import com.rodrigvf.copiou_papelaria_api.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository repository;

    public List<Permission> findAll() {
        return repository.findAll();
    }

    public Optional<Permission> findById(Long id) {
        return repository.findById(id);
    }

    public void registerPermission(String permissionName) {
        if (!repository.existsByName(permissionName)) {
            Permission permission = new Permission();
            permission.setName(permissionName);
            repository.save(permission);
        }
    }

}
