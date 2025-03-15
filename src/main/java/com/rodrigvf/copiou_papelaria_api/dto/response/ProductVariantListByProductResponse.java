package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductVariantListByProductResponse(
        ProductResponse product,
        List<String> sizes,
        List<String> colors
) {
}
