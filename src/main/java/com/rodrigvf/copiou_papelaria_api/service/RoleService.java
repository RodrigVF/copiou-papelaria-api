package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Permission;
import com.rodrigvf.copiou_papelaria_api.entity.Role;
import com.rodrigvf.copiou_papelaria_api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;
    private final PermissionService permissionService;

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    public Role save(Role role) {
        role.setPermissions(this.findPermissions(role.getPermissions()));
        return repository.save(role);
    }

    public Optional<Role> update(Long roleId, Role updateRole) {
        Optional<Role> optRole = repository.findById(roleId);
        if (optRole.isPresent()) {

            List<Permission> permissions = this.findPermissions(updateRole.getPermissions());

            Role role = optRole.get();
            role.setName(updateRole.getName());
            role.setDescription(updateRole.getDescription());
            role.setPermissions(permissions);


            repository.save(role);
            return Optional.of(role);
        }
        return Optional.empty();
    }

    private List<Permission> findPermissions(List<Permission> permissions) {
        List<Permission> permissionsFound = new ArrayList<>();
        permissions.forEach(permission -> permissionService.findById(permission.getId()).ifPresent(permissionsFound::add));
        return permissionsFound;
    }

}
