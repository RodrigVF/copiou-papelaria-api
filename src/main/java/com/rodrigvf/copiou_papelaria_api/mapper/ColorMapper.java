package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.ColorRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.ColorResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Color;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColorMapper {

    public static Color toColor(ColorRequest request) {
        return Color
                .builder()
                .name(request.name())
                .build();
    }

    public static ColorResponse toColorResponse(Color color) {
        return ColorResponse
                .builder()
                .id(color.getId())
                .name(color.getName())
                .build();
    }

}
