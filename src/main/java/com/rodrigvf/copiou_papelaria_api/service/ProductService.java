package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.entity.Product;
import com.rodrigvf.copiou_papelaria_api.repository.BrandRepository;
import com.rodrigvf.copiou_papelaria_api.repository.ProductRepository;
import com.rodrigvf.copiou_papelaria_api.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final BrandService brandService;
    private final ImageService imageService;


    public Page<Product> findAll(Integer page, Integer limit) {
        return repository.findAll(PageRequest.of(page, limit));
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Page<Product> findByParams(Integer page, Integer limit, String sortBy, String sortDirection, Boolean isActive, Long brand) {
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "id";
        }

        Sort sort = Sort.by(Sort.Order.asc(sortBy));
        if ("DESC".equalsIgnoreCase(sortDirection)) {
            sort = Sort.by(Sort.Order.desc(sortBy));
        }

        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<Product> spec = Specification.where(null);

        if (isActive != null) {
            spec = spec.and(ProductSpecification.isActive(isActive));
        }

        if (brand != null) {
            spec = spec.and(ProductSpecification.hasBrand(brand));
        }

        return repository.findAll(spec, pageable);
    }

    public Product save(Product product) {
        product.setBrand(this.findBrand(product.getBrand()));
        product.setImages(this.findImages(product.getImages()));
        return repository.save(product);
    }

    public Optional<Product> update(Long productId, Product updateProduct) {
        Optional<Product> optProduct = repository.findById(productId);
        if (optProduct.isPresent()) {

            Brand brand = this.findBrand(updateProduct.getBrand());
            List<Image> images = this.findImages(updateProduct.getImages());

            Product product = optProduct.get();
            product.setName(updateProduct.getName());
            product.setDescription(updateProduct.getDescription());
            product.setBrand(brand);
            product.setPrice(updateProduct.getPrice());
            product.setIsActive(updateProduct.getIsActive());
            product.setImages(images);

            repository.save(product);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public Optional<Product> changeStatus(Long productId, Boolean active) {
        Optional<Product> optProduct = repository.findById(productId);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            product.setIsActive(active);

            repository.save(product);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    private Brand findBrand(Brand brand) {
        return brandService.findById(brand.getId()).orElse(null);
    }

    private List<Image> findImages(List<Image> images) {
        List<Image> imagesFound = new ArrayList<>();
        images.forEach(image -> imageService.findById(image.getId()).ifPresent(imagesFound::add));
        return imagesFound;
    }

}
