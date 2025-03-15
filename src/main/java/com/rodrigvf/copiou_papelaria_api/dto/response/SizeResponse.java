package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

@Builder
public record SizeResponse(Long id, String name) {
}
