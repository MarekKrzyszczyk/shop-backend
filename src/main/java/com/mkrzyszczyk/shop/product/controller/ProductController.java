package com.mkrzyszczyk.shop.product.controller;

import com.mkrzyszczyk.shop.product.model.Product;
import com.mkrzyszczyk.shop.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/products")
  public List<Product> getProducts() {
    return productService.getProducts();
  }
}