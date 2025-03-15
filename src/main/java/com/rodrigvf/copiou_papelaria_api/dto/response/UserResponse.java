package com.rodrigvf.copiou_papelaria_api.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        Boolean isActive,
        RoleResponse role,
        LocalDateTime lastLogin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
