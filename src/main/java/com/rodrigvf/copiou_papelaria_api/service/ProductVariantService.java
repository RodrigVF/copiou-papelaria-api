package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Color;
import com.rodrigvf.copiou_papelaria_api.entity.Product;
import com.rodrigvf.copiou_papelaria_api.entity.ProductVariant;
import com.rodrigvf.copiou_papelaria_api.entity.Size;
import com.rodrigvf.copiou_papelaria_api.repository.ProductVariantRepository;
import com.rodrigvf.copiou_papelaria_api.specification.ProductVariantSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository repository;
    private final ProductService productService;
    private final ColorService colorService;
    private final SizeService sizeService;

    public List<ProductVariant> findAll() {
        return repository.findAll();
    }

    public Optional<ProductVariant> findById(Long id) {
        return repository.findById(id);
    }

    public List<ProductVariant> findByParams(Long product, Boolean isActive) {
        Specification<ProductVariant> spec = Specification.where(null);

        if (product != null) {
            spec = spec.and(ProductVariantSpecification.hasProduct(product));
        }

        if (isActive != null) {
            spec = spec.and(ProductVariantSpecification.isActive(isActive));
        }

        return repository.findAll(spec);
    }

    public Page<Map<String, Object>> findListAllProducts(Integer page, Integer limit) {
        Page<Product> products = productService.findAll(page, limit);

        List<Map<String, Object>> responseList = new ArrayList<>();

        for (Product product : products) {
            List<ProductVariant> variants = repository.findProductVariantByProduct(product);

            List<String> colors = variants.stream()
                    .map(v -> v.getColor().getName())
                    .distinct()
                    .collect(Collectors.toList());

            List<String> sizes = variants.stream()
                    .map(v -> v.getSize().getName())
                    .distinct()
                    .collect(Collectors.toList());

            Map<String, Object> productData = new HashMap<>();
            productData.put("product", product);
            productData.put("colors", colors);
            productData.put("sizes", sizes);

            responseList.add(productData);
        }

        return new PageImpl<>(responseList, products.getPageable(), products.getTotalElements());
    }


    public Optional<Map<String, Object>> findListByProduct(Long productId) {
        return productService.findById(productId).map(product -> {
            List<ProductVariant> variants = repository.findProductVariantByProduct(Product.builder().id(productId).build());

            List<String> colors = variants.stream()
                    .map(v -> v.getColor().getName())
                    .distinct()
                    .collect(Collectors.toList());

            List<String> sizes = variants.stream()
                    .map(v -> v.getSize().getName())
                    .distinct()
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("product", product);
            response.put("colors", colors);
            response.put("sizes", sizes);

            return response;
        });
    }

    public ProductVariant save(ProductVariant productVariant) {
        productVariant.setProduct(this.findProduct(productVariant.getProduct()));
        productVariant.setColor(this.findColor(productVariant.getColor()));
        productVariant.setSize(this.findSize(productVariant.getSize()));

        return repository.save(productVariant);
    }

    public Optional<ProductVariant> changeStatus(Long productVariantId, Boolean active) {
        Optional<ProductVariant> optProductVariant = repository.findById(productVariantId);
        if (optProductVariant.isPresent()) {
            ProductVariant productVariant = optProductVariant.get();
            productVariant.setIsActive(active);

            repository.save(productVariant);
            return Optional.of(productVariant);
        }
        return Optional.empty();
    }

    private Product findProduct(Product product) {
        return productService.findById(product.getId()).orElse(null);
    }

    private Color findColor(Color color) {
        return colorService.findById(color.getId()).orElse(null);
    }

    private Size findSize(Size size) {
        return sizeService.findById(size.getId()).orElse(null);
    }

}
