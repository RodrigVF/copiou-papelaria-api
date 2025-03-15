package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public void delete(Long productImageId) {
        imageRepository.deleteById(productImageId);
    }
}
