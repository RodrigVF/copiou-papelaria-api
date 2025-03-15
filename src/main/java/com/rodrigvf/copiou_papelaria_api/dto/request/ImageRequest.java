package com.rodrigvf.copiou_papelaria_api.dto.request;

public record ImageRequest(
        String src,
        String alt,
        Boolean isThumbnail,
        Boolean isActive
) {
}
