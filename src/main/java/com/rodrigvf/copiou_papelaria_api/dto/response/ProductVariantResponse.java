package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductVariantResponse(
        Long id,
        ProductResponse product,
        ColorResponse color,
        SizeResponse size,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
