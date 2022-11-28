package com.mkrzyszczyk.shop.product.controller;

import com.mkrzyszczyk.shop.product.model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @GetMapping("/products")
  public List<Product> getProducts() {
    return List.of(new Product("Product 1", "Category 1", "Desc 1", new BigDecimal("10.99"), "PLN"),
        new Product("Product 2", "Category 2", "Desc 2", new BigDecimal("20.99"), "PLN"),
        new Product("Product 3", "Category 3", "Desc 3", new BigDecimal("30.99"), "PLN"),
        new Product("Product 4", "Category 4", "Desc 4", new BigDecimal("99.99"), "PLN"));
  }

}