package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProductRequest (
        @NotBlank(message = "Nome do produto é obrigatório")
        String name,
        String description,
        Long brandId,
        Boolean isActive,
        List<Long> images
) {

}
