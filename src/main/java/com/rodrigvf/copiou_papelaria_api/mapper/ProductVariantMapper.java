package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.request.ProductVariantRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.*;
import com.rodrigvf.copiou_papelaria_api.entity.Color;
import com.rodrigvf.copiou_papelaria_api.entity.Product;
import com.rodrigvf.copiou_papelaria_api.entity.ProductVariant;
import com.rodrigvf.copiou_papelaria_api.entity.Size;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class ProductVariantMapper {

    public static ProductVariant toProductVariant(ProductVariantRequest request) {

        Product product = Product.builder().id(request.productId()).build();

        Color color = Color.builder().id(request.colorId()).build();

        Size size = Size.builder().id(request.sizeId()).build();

        return ProductVariant.builder()
                .product(product)
                .color(color)
                .size(size)
                .isActive(request.isActive())
                .build();
    }


    public static ProductVariantResponse toProductVariantResponse(ProductVariant productVariant) {

        ProductResponse product = productVariant.getProduct() != null ? ProductMapper.toProductResponse(productVariant.getProduct()) : null;
        ColorResponse color = productVariant.getColor() != null ? ColorMapper.toColorResponse(productVariant.getColor()) : null;
        SizeResponse size = productVariant.getSize() != null ? SizeMapper.toSizeResponse(productVariant.getSize()) : null;

        return ProductVariantResponse.builder()
                .id(productVariant.getId())
                .product(product)
                .color(color)
                .size(size)
                .isActive(productVariant.getIsActive())
                .createdAt(productVariant.getCreatedAt())
                .updatedAt(productVariant.getUpdatedAt())
                .build();
    }

    public static ProductVariantListByProductResponse toProductVariantListByProductResponse(Map<String, Object> data) {
        Product product = (Product) data.get("product");

        List<?> colorsObject = (List<?>) data.get("colors");
        List<?> sizesObject = (List<?>) data.get("sizes");

        List<String> colors = colorsObject.stream()
                .filter(e -> e instanceof String)
                .map(e -> (String) e)
                .collect(Collectors.toList());

        List<String> sizes = sizesObject.stream()
                .filter(e -> e instanceof String)
                .map(e -> (String) e)
                .collect(Collectors.toList());

        return ProductVariantListByProductResponse.builder()
                .product(ProductMapper.toProductResponse(product))
                .colors(colors)
                .sizes(sizes)
                .build();
    }

}
