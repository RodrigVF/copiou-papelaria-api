package com.rodrigvf.copiou_papelaria_api.dto.response;

public record PaginationResponse(
        int currentPage,
        int totalPages,
        long totalElements
) {

}
