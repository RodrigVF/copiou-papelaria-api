package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.AddressRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.AddressResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Address;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AddressMapper {

    public static Address toAddress(AddressRequest request) {
        return Address.builder()
                .street(request.street())
                .number(request.number())
                .complement(request.complement())
                .city(request.city())
                .state(request.state())
                .country(request.country())
                .postalCode(request.postalCode())
                .build();
    }

    public static AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }

}
