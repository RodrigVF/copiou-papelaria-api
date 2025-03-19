package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

public record PrintShopItemRequest(
        @NotBlank(message = "Nome do item é obrigatório")
        String name,
        String description,
        BigDecimal price,
        Long duration,
        Boolean isActive,
        List<Long> images
) {

}
