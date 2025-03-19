package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.AddressRequest;
import com.rodrigvf.copiou_papelaria_api.dto.request.BrandRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.AddressResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.BrandResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Address;
import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import com.rodrigvf.copiou_papelaria_api.mapper.AddressMapper;
import com.rodrigvf.copiou_papelaria_api.mapper.BrandMapper;
import com.rodrigvf.copiou_papelaria_api.service.AddressService;
import com.rodrigvf.copiou_papelaria_api.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping()
    public ResponseEntity<List<AddressResponse>> getAll() {
        return  ResponseEntity.ok(addressService.findAll()
                .stream()
                .map(AddressMapper::toAddressResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable Long id) {
        return addressService.findById(id)
                .map(address -> ResponseEntity.ok(AddressMapper.toAddressResponse(address)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('address:create')")
    public ResponseEntity<AddressResponse> save(@Valid @RequestBody AddressRequest request) {
        Address newAddress = AddressMapper.toAddress(request);
        Address savedAddress = addressService.save(newAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(AddressMapper.toAddressResponse(savedAddress));
    }


}
