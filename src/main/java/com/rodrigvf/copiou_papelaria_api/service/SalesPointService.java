package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Address;
import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.entity.SalesPoint;
import com.rodrigvf.copiou_papelaria_api.repository.SalesPointRepository;
import com.rodrigvf.copiou_papelaria_api.specification.SalesPointSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalesPointService {

    private final SalesPointRepository repository;
    private final AddressService addressService;
    private final ImageService imageService;


    public Page<SalesPoint> findAll(Integer page, Integer limit) {
        return repository.findAll(PageRequest.of(page, limit));
    }

    public Optional<SalesPoint> findById(Long id) {
        return repository.findById(id);
    }

    public Page<SalesPoint> findByParams(Integer page, Integer limit, Boolean isActive) {
        Pageable pageable = PageRequest.of(page, limit);
        Specification<SalesPoint> spec = Specification.where(null);

        if (isActive != null) {
            spec = spec.and(SalesPointSpecification.isActive(isActive));
        }

        return repository.findAll(spec, pageable);
    }

    public SalesPoint save(SalesPoint salesPoint) {
        salesPoint.setImages(this.findImages(salesPoint.getImages()));
        return repository.save(salesPoint);
    }

    public Optional<SalesPoint> update(Long salesPointId, SalesPoint updateSalesPoint) {
        Optional<SalesPoint> optSalesPoint = repository.findById(salesPointId);
        if (optSalesPoint.isPresent()) {

            List<Image> images = this.findImages(updateSalesPoint.getImages());

            SalesPoint salesPoint = optSalesPoint.get();
            salesPoint.setName(updateSalesPoint.getName());
            salesPoint.setPhone(updateSalesPoint.getPhone());
            salesPoint.setEmail(updateSalesPoint.getEmail());
            salesPoint.setBusinessHours(updateSalesPoint.getBusinessHours());
            salesPoint.setGeolocation(updateSalesPoint.getGeolocation());
            salesPoint.setIsMainSalesPoint(updateSalesPoint.getIsMainSalesPoint());
            salesPoint.setIsActive(updateSalesPoint.getIsActive());
            salesPoint.setImages(images);

            if (updateSalesPoint.getAddress() != null) {
                salesPoint.setAddress(updateSalesPoint.getAddress());
            }

            repository.save(salesPoint);
            return Optional.of(salesPoint);
        }
        return Optional.empty();
    }

    public Optional<SalesPoint> changeStatus(Long salesPointId, Boolean active) {
        Optional<SalesPoint> optSalesPoint = repository.findById(salesPointId);
        if (optSalesPoint.isPresent()) {
            SalesPoint salesPoint = optSalesPoint.get();
            salesPoint.setIsActive(active);

            repository.save(salesPoint);
            return Optional.of(salesPoint);
        }
        return Optional.empty();
    }

    private Address findAddress(Address address) {
        return addressService.findById(address.getId()).orElse(null);
    }

    private List<Image> findImages(List<Image> images) {
        List<Image> imagesFound = new ArrayList<>();
        images.forEach(image -> imageService.findById(image.getId()).ifPresent(imagesFound::add));
        return imagesFound;
    }

}
