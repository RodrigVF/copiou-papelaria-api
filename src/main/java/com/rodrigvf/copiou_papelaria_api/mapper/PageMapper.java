package com.rodrigvf.copiou_papelaria_api.mapper;

import com.rodrigvf.copiou_papelaria_api.dto.response.PageResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.PaginationResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PageMapper {
    public static <T, R> PageResponse<R> toPagedResponse(Page<T> page, Function<T, R> converter) {
        List<R> content = page.getContent().stream().map(converter).toList();

        PaginationResponse pagination = new PaginationResponse(
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );

        return new PageResponse<>(content, pagination);
    }
}
