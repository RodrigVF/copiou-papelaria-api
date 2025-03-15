package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.SizeRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.SizeResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Size;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SizeMapper {

    public static Size toSize(SizeRequest request) {
        return Size
                .builder()
                .name(request.name())
                .build();
    }

    public static SizeResponse toSizeResponse(Size size) {
        return SizeResponse
                .builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }

}
