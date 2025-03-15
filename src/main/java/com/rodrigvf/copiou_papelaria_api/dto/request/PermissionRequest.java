package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PermissionRequest(
        @NotBlank(message = "Nome do cargo é obrigatório") String name,
        String description
) {

}
