package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.ImageRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.ImageResponse;
import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.mapper.ImageMapper;
import com.rodrigvf.copiou_papelaria_api.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<List<ImageResponse>> findAll() {
        return ResponseEntity.ok(imageService.findAll()
                .stream()
                .map(ImageMapper::toImageResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> findById(@PathVariable Long id) {
        return imageService.findById(id)
                .map(image -> ResponseEntity.ok(ImageMapper.toImageResponse(image)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('image:create')")
    public ResponseEntity<ImageResponse> save(@Valid @RequestBody ImageRequest request) {
        Image savedImage = imageService.save(ImageMapper.toImage(request));
        return ResponseEntity.ok(ImageMapper.toImageResponse(savedImage));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('image:delete')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Image> optImage = imageService.findById(id);
        if (optImage.isPresent()) {
            imageService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
