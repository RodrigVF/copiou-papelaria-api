package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.ProductRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.BrandResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.ImageResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.ProductResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.entity.Product;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ProductMapper {

    public static Product toProduct(ProductRequest request) {

        Brand brand = Brand.builder().id(request.brandId()).build();

        List<Image> images = request.images().stream()
                .map(imageId -> Image.builder().id(imageId).build())
                .toList();

        return Product.builder()
                .name(request.name())
                .description(request.description())
                .brand(brand)
                .isActive(request.isActive())
                .images(images)
                .build();
    }

    public static ProductResponse toProductResponse(Product product) {

        BrandResponse brand = product.getBrand() != null ? BrandMapper.toBrandResponse(product.getBrand()) : null;

        List<ImageResponse> images = product.getImages()
                .stream()
                .map(ImageMapper::toImageResponse)
                .toList();

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(brand)
                .isActive(product.getIsActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .images(images)
                .build();
    }

}
