package com.rodrigvf.copiou_papelaria_api.service;

import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.entity.PrintShopItem;
import com.rodrigvf.copiou_papelaria_api.repository.PrintShopItemRepository;
import com.rodrigvf.copiou_papelaria_api.specification.PrintShopItemSpecification;
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
public class PrintShopItemService {

    private final PrintShopItemRepository repository;
    private final ImageService imageService;


    public Page<PrintShopItem> findAll(Integer page, Integer limit) {
        return repository.findAll(PageRequest.of(page, limit));
    }

    public Optional<PrintShopItem> findById(Long id) {
        return repository.findById(id);
    }

    public Page<PrintShopItem> findByParams(Integer page, Integer limit, Boolean isActive) {
        Pageable pageable = PageRequest.of(page, limit);
        Specification<PrintShopItem> spec = Specification.where(null);

        if (isActive != null) {
            spec = spec.and(PrintShopItemSpecification.isActive(isActive));
        }

        return repository.findAll(spec, pageable);
    }

    public PrintShopItem save(PrintShopItem printShopItem) {
        printShopItem.setImages(this.findImages(printShopItem.getImages()));
        return repository.save(printShopItem);
    }

    public Optional<PrintShopItem> update(Long printShopItemId, PrintShopItem updatePrintShopItem) {
        Optional<PrintShopItem> optPrintShopItem = repository.findById(printShopItemId);
        if (optPrintShopItem.isPresent()) {

            List<Image> images = this.findImages(updatePrintShopItem.getImages());

            PrintShopItem printShopItem = optPrintShopItem.get();
            printShopItem.setName(updatePrintShopItem.getName());
            printShopItem.setDescription(updatePrintShopItem.getDescription());
            printShopItem.setPrice(updatePrintShopItem.getPrice());
            printShopItem.setDuration(updatePrintShopItem.getDuration());
            printShopItem.setIsActive(updatePrintShopItem.getIsActive());
            printShopItem.setImages(images);

            repository.save(printShopItem);
            return Optional.of(printShopItem);
        }
        return Optional.empty();
    }

    public Optional<PrintShopItem> changeStatus(Long printShopItemId, Boolean active) {
        Optional<PrintShopItem> optPrintShopItem = repository.findById(printShopItemId);
        if (optPrintShopItem.isPresent()) {
            PrintShopItem printShopItem = optPrintShopItem.get();
            printShopItem.setIsActive(active);

            repository.save(printShopItem);
            return Optional.of(printShopItem);
        }
        return Optional.empty();
    }

    private List<Image> findImages(List<Image> images) {
        List<Image> imagesFound = new ArrayList<>();
        images.forEach(image -> imageService.findById(image.getId()).ifPresent(imagesFound::add));
        return imagesFound;
    }

}
