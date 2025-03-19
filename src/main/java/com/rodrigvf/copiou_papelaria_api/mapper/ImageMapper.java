package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.ImageRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.ImageResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Image;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageMapper {

    public static Image toImage(ImageRequest request) {
        return Image.builder()
                .src(request.src())
                .alt(request.alt())
                .isThumbnail(request.isThumbnail())
                .isBanner(request.isBanner())
                .isActive(request.isActive())
                .build();
    }

    public static ImageResponse toImageResponse(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .src(image.getSrc())
                .alt(image.getAlt())
                .isThumbnail(image.getIsThumbnail())
                .isBanner(image.getIsBanner())
                .isActive(image.getIsActive())
                .createdAt(image.getCreatedAt())
                .updatedAt(image.getUpdatedAt())
                .build();
    }

}
