package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.ColorRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.ColorResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Color;
import com.rodrigvf.copiou_papelaria_api.mapper.ColorMapper;
import com.rodrigvf.copiou_papelaria_api.service.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping()
    public ResponseEntity<List<ColorResponse>> getAll() {
        return  ResponseEntity.ok(colorService.findAll()
                .stream()
                .map(ColorMapper::toColorResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorResponse> getById(@PathVariable Long id) {
        return colorService.findById(id)
                .map(color -> ResponseEntity.ok(ColorMapper.toColorResponse(color)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('color:create')")
    public ResponseEntity<ColorResponse> save(@Valid @RequestBody ColorRequest request) {
        Color newColor = ColorMapper.toColor(request);
        Color savedColor = colorService.save(newColor);
        return ResponseEntity.status(HttpStatus.CREATED).body(ColorMapper.toColorResponse(savedColor));
    }

}
