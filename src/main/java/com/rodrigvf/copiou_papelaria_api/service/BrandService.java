package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import com.rodrigvf.copiou_papelaria_api.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository repository;

    public List<Brand> findAll() {
        return repository.findAll();
    }

    public Optional<Brand> findById(Long id) {
        return repository.findById(id);
    }

    public Brand save(Brand brand) {
        return repository.save(brand);
    }

}
