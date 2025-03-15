package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

@Builder
public record PermissionResponse(
        Long id,
        String name,
        String description
) {
}
