package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.dto.request.PrintShopItemRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.PageResponse;
import com.rodrigvf.copiou_papelaria_api.dto.response.PrintShopItemResponse;
import com.rodrigvf.copiou_papelaria_api.entity.PrintShopItem;
import com.rodrigvf.copiou_papelaria_api.mapper.PageMapper;
import com.rodrigvf.copiou_papelaria_api.mapper.PrintShopItemMapper;
import com.rodrigvf.copiou_papelaria_api.service.PrintShopItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/print-shop-item")
@RequiredArgsConstructor
public class PrintShopItemController {

    private final PrintShopItemService printShopItemService;

    @GetMapping
    public ResponseEntity<PageResponse<PrintShopItemResponse>> findAll(
            @RequestParam(defaultValue = "0", required = false)
            Integer page,
            @RequestParam(defaultValue = "10", required = false)
            Integer limit
    ) {
        Page<PrintShopItem> printShopItemPage = printShopItemService.findAll(page, limit);

        PageResponse<PrintShopItemResponse> response = PageMapper.toPagedResponse(printShopItemPage, PrintShopItemMapper::toPrintShopItemResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrintShopItemResponse> findById(@PathVariable Long id) {
        return printShopItemService.findById(id)
                .map(printShopItem -> ResponseEntity.ok(PrintShopItemMapper.toPrintShopItemResponse(printShopItem)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<PrintShopItemResponse>> findByParams(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer limit,
            @RequestParam(required = false) Boolean active
    ) {
        Page<PrintShopItem> printShopItemPage = printShopItemService.findByParams(page, limit, active);

        PageResponse<PrintShopItemResponse> response = PageMapper.toPagedResponse(printShopItemPage, PrintShopItemMapper::toPrintShopItemResponse);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('print_shop_item:create')")
    public ResponseEntity<PrintShopItemResponse> save(@Valid @RequestBody PrintShopItemRequest request) {
        PrintShopItem savedPrintShopItem = printShopItemService.save(PrintShopItemMapper.toPrintShopItem(request));
        return ResponseEntity.ok(PrintShopItemMapper.toPrintShopItemResponse(savedPrintShopItem));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('print_shop_item:update')")
    public ResponseEntity<PrintShopItemResponse> update(@PathVariable Long id, @Valid @RequestBody PrintShopItemRequest request) {
        return printShopItemService.update(id, PrintShopItemMapper.toPrintShopItem(request))
                .map(printShopItem -> ResponseEntity.ok(PrintShopItemMapper.toPrintShopItemResponse(printShopItem)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('print_shop_item:deactivate')")
    public ResponseEntity<PrintShopItemResponse> inactivate(@PathVariable Long id) {
        return printShopItemService.changeStatus(id, false)
                .map(printShopItem -> ResponseEntity.ok(PrintShopItemMapper.toPrintShopItemResponse(printShopItem)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/activate/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('print_shop_item:activate')")
    public ResponseEntity<PrintShopItemResponse> activate(@PathVariable Long id) {
        return printShopItemService.changeStatus(id, true)
                .map(printShopItem -> ResponseEntity.ok(PrintShopItemMapper.toPrintShopItemResponse(printShopItem)))
                .orElse(ResponseEntity.notFound().build());
    }

}
