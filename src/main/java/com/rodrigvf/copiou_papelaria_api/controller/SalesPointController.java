package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.SalesPointRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.PageResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.SalesPointResponse;
import com.rodrigvf.copiou_papelaria_api.entity.SalesPoint;
import com.rodrigvf.copiou_papelaria_api.mapper.PageMapper;
import com.rodrigvf.copiou_papelaria_api.mapper.SalesPointMapper;
import com.rodrigvf.copiou_papelaria_api.service.SalesPointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales-point")
@RequiredArgsConstructor
public class SalesPointController {

    private final SalesPointService salesPointService;

    @GetMapping
    public ResponseEntity<PageResponse<SalesPointResponse>> findAll(
            @RequestParam(defaultValue = "0", required = false)
            Integer page,
            @RequestParam(defaultValue = "10", required = false)
            Integer limit
    ) {
        Page<SalesPoint> salesPointPage = salesPointService.findAll(page, limit);

        PageResponse<SalesPointResponse> response = PageMapper.toPagedResponse(salesPointPage, SalesPointMapper::toSalesPointResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesPointResponse> findById(@PathVariable Long id) {
        return salesPointService.findById(id)
                .map(salesPoint -> ResponseEntity.ok(SalesPointMapper.toSalesPointResponse(salesPoint)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<SalesPointResponse>> findByParams(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer limit,
            @RequestParam(required = false) Boolean active
    ) {
        Page<SalesPoint> salesPointPage = salesPointService.findByParams(page, limit, active);

        PageResponse<SalesPointResponse> response = PageMapper.toPagedResponse(salesPointPage, SalesPointMapper::toSalesPointResponse);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('sales_point:create')")
    public ResponseEntity<SalesPointResponse> save(@Valid @RequestBody SalesPointRequest request) {
        SalesPoint savedSalesPoint = salesPointService.save(SalesPointMapper.toSalesPoint(request));
        return ResponseEntity.ok(SalesPointMapper.toSalesPointResponse(savedSalesPoint));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('sales_point:update')")
    public ResponseEntity<SalesPointResponse> update(@PathVariable Long id, @Valid @RequestBody SalesPointRequest request) {
        return salesPointService.update(id, SalesPointMapper.toSalesPoint(request))
                .map(salesPoint -> ResponseEntity.ok(SalesPointMapper.toSalesPointResponse(salesPoint)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('sales_point:deactivate')")
    public ResponseEntity<SalesPointResponse> inactivate(@PathVariable Long id) {
        return salesPointService.changeStatus(id, false)
                .map(salesPoint -> ResponseEntity.ok(SalesPointMapper.toSalesPointResponse(salesPoint)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/activate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('sales_point:activate')")
    public ResponseEntity<SalesPointResponse> activate(@PathVariable Long id) {
        return salesPointService.changeStatus(id, true)
                .map(salesPoint -> ResponseEntity.ok(SalesPointMapper.toSalesPointResponse(salesPoint)))
                .orElse(ResponseEntity.notFound().build());
    }

}
