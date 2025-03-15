package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.RoleRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.ImageResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.PermissionResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.RoleResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Permission;
import com.rodrigvf.copiou_papelaria_api.entity.Role;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class RoleMapper {

    public static Role toRole(RoleRequest request) {

        List<Permission> permissions = request.permissions().stream()
                .map(permissionId -> Permission.builder().id(permissionId).build())
                .toList();

        return Role
                .builder()
                .name(request.name())
                .description(request.description())
                .permissions(permissions)
                .build();
    }

    public static RoleResponse toRoleResponse(Role role) {

        List<PermissionResponse> permissions = role.getPermissions()
                .stream()
                .map(PermissionMapper::toPermissionResponse)
                .toList();

        return RoleResponse
                .builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .permissions(permissions)
                .build();
    }
}
