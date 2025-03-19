package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PrintShopItemResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Long duration,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ImageResponse> images
) {
}
