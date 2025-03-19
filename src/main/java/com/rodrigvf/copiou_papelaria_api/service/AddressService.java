package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Address;
import com.rodrigvf.copiou_papelaria_api.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Optional<Address> findById(Long id) {
        return repository.findById(id);
    }

    public Address save(Address address) {
        return repository.save(address);
    }

}
