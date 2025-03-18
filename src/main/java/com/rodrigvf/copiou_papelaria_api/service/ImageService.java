package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Page<Image> findAll(Integer page, Integer limit, String sortBy, String sortDirection) {
        return imageRepository.findAll(PageRequest.of(page, limit));
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
