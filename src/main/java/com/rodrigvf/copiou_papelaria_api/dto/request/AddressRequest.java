package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotBlank(message = "Rua é obrigatória")
        String street,
        @NotNull(message = "Número é obrigatório")
        Integer number,
        String complement,
        @NotBlank(message = "Cidade é obrigatória")
        String city,
        @NotBlank(message = "Estado é obrigatório")
        String state,
        String country,
        @NotBlank(message = "CEP é obrigatório")
        String postalCode
) {
}
