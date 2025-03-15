package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.PermissionRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.PermissionResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Permission;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PermissionMapper {

    public static Permission toPermission(PermissionRequest request) {
        return Permission
                .builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public static PermissionResponse toPermissionResponse(Permission permission) {
        return PermissionResponse
                .builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}
