package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

@Builder
public record BrandResponse(Long id, String name) {
}
