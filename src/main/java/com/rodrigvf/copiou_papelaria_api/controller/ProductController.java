package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.ProductRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.PageResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.ProductResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Product;
import com.rodrigvf.copiou_papelaria_api.mapper.PageMapper;
import com.rodrigvf.copiou_papelaria_api.mapper.ProductMapper;
import com.rodrigvf.copiou_papelaria_api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> findAll(
            @RequestParam(defaultValue = "0", required = false)
            Integer page,
            @RequestParam(defaultValue = "10", required = false)
            Integer limit
    ) {
        Page<Product> productPage = productService.findAll(page, limit);

        PageResponse<ProductResponse> response = PageMapper.toPagedResponse(productPage, ProductMapper::toProductResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> ResponseEntity.ok(ProductMapper.toProductResponse(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<ProductResponse>> findByParams(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer limit,
            @RequestParam(defaultValue = "id", required = false)
            String sortBy,
            @RequestParam(defaultValue = "ASC", required = false)
            String sortDirection,
            @RequestParam(required = false) Long brand,
            @RequestParam(required = false) Boolean active
    ) {
        Page<Product> productPage = productService.findByParams(page, limit, sortBy, sortDirection, brand, active);

        PageResponse<ProductResponse> response = PageMapper.toPagedResponse(productPage, ProductMapper::toProductResponse);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product:create')")
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {
        Product savedProduct = productService.save(ProductMapper.toProduct(request));
        return ResponseEntity.ok(ProductMapper.toProductResponse(savedProduct));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product:update')")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return productService.update(id, ProductMapper.toProduct(request))
                .map(product -> ResponseEntity.ok(ProductMapper.toProductResponse(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product:deactivate')")
    public ResponseEntity<ProductResponse> inactivate(@PathVariable Long id) {
        return productService.changeStatus(id, false)
                .map(product -> ResponseEntity.ok(ProductMapper.toProductResponse(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/activate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('product:activate')")
    public ResponseEntity<ProductResponse> activate(@PathVariable Long id) {
        return productService.changeStatus(id, true)
                .map(product -> ResponseEntity.ok(ProductMapper.toProductResponse(product)))
                .orElse(ResponseEntity.notFound().build());
    }

}
