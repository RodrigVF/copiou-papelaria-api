package com.rodrigvf.copiou_papelaria_api.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long id, String username, String email, String authorities) {
}
