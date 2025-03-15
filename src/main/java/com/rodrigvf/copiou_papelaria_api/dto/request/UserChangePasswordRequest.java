package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserChangePasswordRequest(
        @NotBlank(message = "Senha atual é obrigatória")
        String currentPassword,
        @NotBlank(message = "Nova senha é obrigatória")
        String newPassword
) {
}
