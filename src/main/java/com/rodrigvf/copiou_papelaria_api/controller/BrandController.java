package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.BrandRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.BrandResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import com.rodrigvf.copiou_papelaria_api.mapper.BrandMapper;
import com.rodrigvf.copiou_papelaria_api.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping()
    public ResponseEntity<List<BrandResponse>> getAll() {
        return  ResponseEntity.ok(brandService.findAll()
                .stream()
                .map(BrandMapper::toBrandResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getById(@PathVariable Long id) {
        return brandService.findById(id)
                .map(brand -> ResponseEntity.ok(BrandMapper.toBrandResponse(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('brand:create')")
    public ResponseEntity<BrandResponse> save(@Valid @RequestBody BrandRequest request) {
        Brand newBrand = BrandMapper.toBrand(request);
        Brand savedBrand = brandService.save(newBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(BrandMapper.toBrandResponse(savedBrand));
    }


}
