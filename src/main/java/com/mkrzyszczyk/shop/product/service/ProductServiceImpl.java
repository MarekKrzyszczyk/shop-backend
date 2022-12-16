package com.mkrzyszczyk.shop.product.service;

import com.mkrzyszczyk.shop.product.model.Product;
import com.mkrzyszczyk.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public Page<Product> getProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @Override
  public Product getProductBySlug(String slug) {
    return productRepository.findProductBySlug(slug).orElseThrow();
  }

}