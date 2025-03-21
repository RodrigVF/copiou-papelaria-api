package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.entity.Product;
import com.rodrigvf.copiou_papelaria_api.repository.ImageRepository;
import com.rodrigvf.copiou_papelaria_api.specification.ImageSpecification;
import com.rodrigvf.copiou_papelaria_api.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository repository;

    public Page<Image> findAll(Integer page, Integer limit, String sortBy, String sortDirection) {
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }

        Sort sort = "DESC".equalsIgnoreCase(sortDirection) ? Sort.by(Sort.Order.desc(sortBy)) : Sort.by(Sort.Order.asc(sortBy));
        Pageable pageable = PageRequest.of(page, limit, sort);

        return repository.findAll(pageable);
    }

    public Optional<Image> findById(Long id) {
        return repository.findById(id);
    }

    public Page<Image> findByParams(Integer page, Integer limit, String sortBy, String sortDirection, Boolean isActive, Boolean isBanner) {
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }

        Sort sort = Sort.by(Sort.Order.asc(sortBy));
        if ("DESC".equalsIgnoreCase(sortDirection)) {
            sort = Sort.by(Sort.Order.desc(sortBy));
        }

        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<Image> spec = Specification.where(null);

        if (isActive != null) {
            spec = spec.and(ImageSpecification.isActive(isActive));
        }

        if (isBanner != null) {
            spec = spec.and(ImageSpecification.isBanner(isBanner));
        }

        return repository.findAll(spec, pageable);
    }

    public Image save(Image image) {
        return repository.save(image);
    }

    public Optional<Image> changeImageAttribute(Long imageId, Boolean active, String attribute) {
        Optional<Image> optImage = repository.findById(imageId);
        if (optImage.isPresent()) {
            Image image = optImage.get();

            if ("isThumbnail".equals(attribute)) {
                image.setIsThumbnail(active);
            } else if ("isBanner".equals(attribute)) {
                image.setIsBanner(active);
            } else if ("isActive".equals(attribute)) {
                image.setIsActive(active);
            }  else {
                return Optional.empty();
            }

            repository.save(image);
            return Optional.of(image);
        }
        return Optional.empty();
    }

    public void delete(Long productImageId) {
        repository.deleteById(productImageId);
    }
}
