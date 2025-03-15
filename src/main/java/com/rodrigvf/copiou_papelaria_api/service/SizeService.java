package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Size;
import com.rodrigvf.copiou_papelaria_api.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeService {

    private final SizeRepository repository;

    public List<Size> findAll() {
        return repository.findAll();
    }

    public Optional<Size> findById(Long id) {
        return repository.findById(id);
    }

    public Size save(Size size) {
        return repository.save(size);
    }

}
