package com.rodrigvf.copiou_papelaria_api.dto.response;

import lombok.Builder;

@Builder
public record AddressResponse(
        Long id,
        String street,
        Integer number,
        String complement,
        String city,
        String state,
        String country,
        String postalCode
) {
}
