package com.rodrigvf.copiou_papelaria_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record SalesPointRequest(
        @NotBlank(message = "Nome do ponto de venda é obrigatório")
        @Size(max = 100, message = "Nome não pode ter mais de 100 caracteres")
        String name,

        @NotNull(message = "Endereço é obrigatório")
        Long addressId,

        @Size(max = 20, message = "Telefone não pode ter mais de 20 caracteres")
        String phone,

        @Email(message = "E-mail inválido")
        @Size(max = 100, message = "E-mail não pode ter mais de 100 caracteres")
        String email,

        @Size(max = 255, message = "Horário de funcionamento não pode ter mais de 255 caracteres")
        String businessHours,

        String geolocation,

        Boolean isMainSalesPoint,

        @NotNull(message = "Status ativo é obrigatório")
        Boolean isActive,

        List<Long> images
) {

}
