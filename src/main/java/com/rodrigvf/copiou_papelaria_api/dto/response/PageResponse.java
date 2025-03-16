package com.rodrigvf.copiou_papelaria_api.dto.response;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        PaginationResponse pagination
) {
}
