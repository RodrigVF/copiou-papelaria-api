package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ImageResponse(
        Long id,
        String src,
        String alt,
        Boolean isThumbnail,
        Boolean isBanner,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
