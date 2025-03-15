package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.ProductVariantRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.ProductVariantListByProductResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.ProductVariantResponse;
import com.rodrigvf.copiou_papelaria_api.entity.ProductVariant;
import com.rodrigvf.copiou_papelaria_api.mapper.ProductVariantMapper;
import com.rodrigvf.copiou_papelaria_api.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/variant")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @GetMapping
    public ResponseEntity<List<ProductVariantResponse>> findAll() {
        return ResponseEntity.ok(productVariantService.findAll()
                .stream()
                .map(ProductVariantMapper::toProductVariantResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVariantResponse> findById(@PathVariable Long id) {
        return productVariantService.findById(id)
                .map(productVariant -> ResponseEntity.ok(ProductVariantMapper.toProductVariantResponse(productVariant)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductVariantResponse>> findByParams(
            @RequestParam(required = false) Long product,
            @RequestParam(required = false) Boolean active
    ) {
        List<ProductVariant> productVariants = productVariantService.findByParams(product, active);

        return ResponseEntity.ok(productVariants.stream()
                .map(ProductVariantMapper::toProductVariantResponse)
                .toList());
    }

    @GetMapping("/tags")
    public ResponseEntity<ProductVariantListByProductResponse> findListByProduct(@RequestParam Long product) {
        return productVariantService.findListByProduct(product)
                .map(data -> {
                    ProductVariantListByProductResponse response = ProductVariantMapper.toProductVariantListByProductResponse(data);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product_variant:create')")
    public ResponseEntity<ProductVariantResponse> save(@Valid @RequestBody ProductVariantRequest request) {
        ProductVariant savedProductVariant = productVariantService.save(ProductVariantMapper.toProductVariant(request));
        return ResponseEntity.ok(ProductVariantMapper.toProductVariantResponse(savedProductVariant));
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product_variant:deactivate')")
    public ResponseEntity<ProductVariantResponse> inactivate(@PathVariable Long id) {
        return productVariantService.changeStatus(id, false)
                .map(productVariant -> ResponseEntity.ok(ProductVariantMapper.toProductVariantResponse(productVariant)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/activate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product_variant:activate')")
    public ResponseEntity<ProductVariantResponse> activate(@PathVariable Long id) {
        return productVariantService.changeStatus(id, true)
                .map(productVariant -> ResponseEntity.ok(ProductVariantMapper.toProductVariantResponse(productVariant)))
                .orElse(ResponseEntity.notFound().build());
    }

}
