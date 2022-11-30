package com.mkrzyszczyk.shop.admin.controller;

import com.mkrzyszczyk.shop.admin.model.AdminProduct;
import com.mkrzyszczyk.shop.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

  private final AdminProductService adminProductService;

  @GetMapping("/admin/products")
  public ResponseEntity<Page<AdminProduct>> getProducts(Pageable pageable) {
    return ResponseEntity.ok(adminProductService.getProducts(pageable));
  }

}
