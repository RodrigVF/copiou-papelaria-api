package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SizeRequest (@NotBlank(message = "Nome do tamanho é obrigatório") String name) {
}
