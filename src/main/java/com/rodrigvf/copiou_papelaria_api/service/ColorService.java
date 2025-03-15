package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Color;
import com.rodrigvf.copiou_papelaria_api.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final ColorRepository repository;

    public List<Color> findAll() {
        return repository.findAll();
    }

    public Optional<Color> findById(Long id) {
        return repository.findById(id);
    }

    public Color save(Color color) {
        return repository.save(color);
    }

}
