package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Nome do usuário é obrigatório")
        String username,
        @NotBlank(message = "É necessário informar uma senha")
        String password,
        @NotBlank(message = "E-mail do usuário é obrigatório")
        String email,
        String firstName,
        String lastName,
        Boolean isActive,
        Long roleId
) {
}
