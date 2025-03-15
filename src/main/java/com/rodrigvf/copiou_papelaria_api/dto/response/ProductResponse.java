package com.rodrigvf.copiou_papelaria_api.dto.response;

import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductResponse (
        Long id,
        String name,
        String description,
        BrandResponse brand,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ImageResponse> images
) {
}
