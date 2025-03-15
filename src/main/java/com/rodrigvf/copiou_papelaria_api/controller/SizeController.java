package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.SizeRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.SizeResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Size;
import com.rodrigvf.copiou_papelaria_api.mapper.SizeMapper;
import com.rodrigvf.copiou_papelaria_api.service.SizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/size")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    @GetMapping()
    public ResponseEntity<List<SizeResponse>> getAll() {
        return  ResponseEntity.ok(sizeService.findAll()
                .stream()
                .map(SizeMapper::toSizeResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeResponse> getById(@PathVariable Long id) {
        return sizeService.findById(id)
                .map(size -> ResponseEntity.ok(SizeMapper.toSizeResponse(size)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('size:create')")
    public ResponseEntity<SizeResponse> save(@Valid @RequestBody SizeRequest request) {
        Size newSize = SizeMapper.toSize(request);
        Size savedSize = sizeService.save(newSize);
        return ResponseEntity.status(HttpStatus.CREATED).body(SizeMapper.toSizeResponse(savedSize));
    }

}
