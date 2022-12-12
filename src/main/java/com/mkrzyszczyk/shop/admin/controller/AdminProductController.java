package com.mkrzyszczyk.shop.admin.controller;

import com.mkrzyszczyk.shop.admin.model.AdminProduct;
import com.mkrzyszczyk.shop.admin.model.dto.AdminProductDto;
import com.mkrzyszczyk.shop.admin.service.AdminProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

  private final AdminProductService adminProductService;

  @GetMapping("/admin/products")
  public ResponseEntity<Page<AdminProduct>> getProducts(Pageable pageable) {
    return ResponseEntity.ok(adminProductService.getProducts(pageable));
  }

  @GetMapping("/admin/products/{id}")
  public ResponseEntity<AdminProduct> getProduct(@PathVariable Long id) {
    return ResponseEntity.ok(adminProductService.getProduct(id));
  }

  @PostMapping("/admin/products")
  public ResponseEntity<AdminProduct> createProduct(@RequestBody @Valid AdminProductDto adminProductDto) {
    return new ResponseEntity<>(adminProductService.createProduct(adminProductDto), HttpStatus.CREATED);
  }

  @PutMapping("/admin/products/{id}")
  public ResponseEntity<AdminProduct> updateProduct(@RequestBody AdminProductDto adminProductDto,
      @PathVariable Long id) {
    return ResponseEntity.ok(adminProductService.updateProduct(adminProductDto, id));
  }

}
