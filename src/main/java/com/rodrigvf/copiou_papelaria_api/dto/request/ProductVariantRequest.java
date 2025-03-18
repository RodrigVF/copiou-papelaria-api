package com.rodrigvf.copiou_papelaria_api.dto.request;

public record ProductVariantRequest(
        Long barcode,
        Long productId,
        Long colorId,
        Long sizeId,
        Boolean isActive
) {

}
