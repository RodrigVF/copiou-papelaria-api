package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.BrandRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.BrandResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandMapper {
    public static Brand toBrand(BrandRequest request) {
        return Brand
                .builder()
                .name(request.name())
                .build();
    }

    public static BrandResponse toBrandResponse(Brand brand) {
        return BrandResponse
                .builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
