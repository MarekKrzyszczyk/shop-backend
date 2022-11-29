package com.mkrzyszczyk.shop.product.controller;

import com.mkrzyszczyk.shop.product.model.Product;
import com.mkrzyszczyk.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/products")
  public ResponseEntity<Page<Product>> getProducts(Pageable pageable) {
    return ResponseEntity.ok(productService.getProducts(pageable));
  }

  @PostMapping("/products")
  public ResponseEntity<Product> createProduct() {
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }
}