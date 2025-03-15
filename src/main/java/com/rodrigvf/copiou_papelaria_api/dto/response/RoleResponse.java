package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RoleResponse(
        Long id,
        String name,
        String description,
        List<PermissionResponse> permissions
) {
}
