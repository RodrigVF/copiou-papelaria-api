package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.SalesPointRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.*;
import com.rodrigvf.copiou_papelaria_api.entity.*;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SalesPointMapper {

    public static SalesPoint toSalesPoint(SalesPointRequest request) {

        Address address = Address.builder().id(request.addressId()).build();

        List<Image> images = request.images().stream()
                .map(imageId -> Image.builder().id(imageId).build())
                .toList();

        return SalesPoint.builder()
                .name(request.name())
                .address(address)
                .phone(request.phone())
                .email(request.email())
                .businessHours(request.businessHours())
                .geolocation(request.geolocation())
                .isMainSalesPoint(request.isMainSalesPoint() != null ? request.isMainSalesPoint() : false)
                .isActive(request.isActive())
                .images(images)
                .build();
    }

    public static SalesPointResponse toSalesPointResponse(SalesPoint salesPoint) {

        AddressResponse address = salesPoint.getAddress() != null ? AddressMapper.toAddressResponse(salesPoint.getAddress()) : null;

        List<ImageResponse> images = salesPoint.getImages()
                .stream()
                .map(ImageMapper::toImageResponse)
                .toList();

        return SalesPointResponse.builder()
                .id(salesPoint.getId())
                .name(salesPoint.getName())
                .address(address)
                .phone(salesPoint.getPhone())
                .email(salesPoint.getEmail())
                .businessHours(salesPoint.getBusinessHours())
                .geolocation(salesPoint.getGeolocation())
                .isMainSalesPoint(salesPoint.getIsMainSalesPoint())
                .isActive(salesPoint.getIsActive())
                .createdAt(salesPoint.getCreatedAt())
                .updatedAt(salesPoint.getUpdatedAt())
                .images(images)
                .build();
    }

}
