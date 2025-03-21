package com.rodrigvf.copiou_papelaria_api.dto.request;

import java.util.List;

public class ChangeImagesRequest {
    private List<Long> imagesIds;

    // Getters e Setters
    public List<Long> getImagesIds() {
        return imagesIds;
    }

    public void setImagesIds(List<Long> imagesIds) {
        this.imagesIds = imagesIds;
    }
}