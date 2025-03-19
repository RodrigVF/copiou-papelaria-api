package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record SalesPointResponse(
        Long id,
        String name,
        AddressResponse address,
        String phone,
        String email,
        String businessHours,
        String geolocation,
        Boolean isMainSalesPoint,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ImageResponse> images
) {
}
