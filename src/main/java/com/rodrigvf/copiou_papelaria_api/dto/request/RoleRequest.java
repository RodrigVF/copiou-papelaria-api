package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RoleRequest(
        @NotBlank(message = "Nome do cargo é obrigatório")
        String name,
        String description,
        List<Long> permissions
) {

}
