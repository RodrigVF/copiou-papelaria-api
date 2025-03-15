package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BrandRequest(@NotBlank(message = "Nome da marca é obrigatório") String name) {
}
