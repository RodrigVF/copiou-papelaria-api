package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ColorRequest(@NotBlank(message = "Nome da cor é obrigatório") String name) {
}
