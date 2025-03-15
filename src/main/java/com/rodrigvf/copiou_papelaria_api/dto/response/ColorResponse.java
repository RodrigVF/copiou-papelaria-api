package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

@Builder
public record ColorResponse(Long id, String name) {
}
