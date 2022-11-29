package com.mkrzyszczyk.shop.product.service;

import com.mkrzyszczyk.shop.product.model.Product;
import com.mkrzyszczyk.shop.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public List<Product> getProducts() {
    return productRepository.findAll();
  }

}