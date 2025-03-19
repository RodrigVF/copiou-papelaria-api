package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.PrintShopItemRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.ImageResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.PrintShopItemResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.entity.PrintShopItem;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class PrintShopItemMapper {

    public static PrintShopItem toPrintShopItem(PrintShopItemRequest request) {

        List<Image> images = request.images().stream()
                .map(imageId -> Image.builder().id(imageId).build())
                .toList();

        return PrintShopItem.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .duration(request.duration())
                .isActive(request.isActive())
                .images(images)
                .build();
    }

    public static PrintShopItemResponse toPrintShopItemResponse(PrintShopItem printShopItem) {

        List<ImageResponse> images = printShopItem.getImages()
                .stream()
                .map(ImageMapper::toImageResponse)
                .toList();

        return PrintShopItemResponse.builder()
                .id(printShopItem.getId())
                .name(printShopItem.getName())
                .description(printShopItem.getDescription())
                .price(printShopItem.getPrice())
                .duration(printShopItem.getDuration())
                .isActive(printShopItem.getIsActive())
                .createdAt(printShopItem.getCreatedAt())
                .updatedAt(printShopItem.getUpdatedAt())
                .images(images)
                .build();
    }

}
