package com.mkrzyszczyk.shop.product.controller;

import com.mkrzyszczyk.shop.common.model.Product;
import com.mkrzyszczyk.shop.common.dto.ProductListDto;
import com.mkrzyszczyk.shop.product.dto.ProductDto;
import com.mkrzyszczyk.shop.product.service.ProductService;
import javax.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {

  private final ProductService productService;

  @GetMapping("/products")
  public ResponseEntity<Page<ProductListDto>> getProducts(Pageable pageable) {
    return ResponseEntity.ok(productService.getProducts(pageable));
  }

  @GetMapping("/products/{slug}")
  public ResponseEntity<ProductDto> getProductBySlug(@PathVariable @Pattern(regexp = "[a-z0-9\\-]+")
                                                                @Length(max = 255) String slug) {
    return ResponseEntity.ok(productService.getProductBySlug(slug));
  }

  @PostMapping("/products")
  public ResponseEntity<Product> createProduct() {
    return new ResponseEntity<>(null, HttpStatus.CREATED);
  }
}