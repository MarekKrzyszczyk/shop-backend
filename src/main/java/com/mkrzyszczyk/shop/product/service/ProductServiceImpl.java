package com.mkrzyszczyk.shop.product.service;

import com.mkrzyszczyk.shop.common.dto.ProductListDto;
import com.mkrzyszczyk.shop.common.mapping.ProductListMapping;
import com.mkrzyszczyk.shop.common.model.Product;
import com.mkrzyszczyk.shop.common.repository.ProductRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductListMapping productListMapping;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
    this.productListMapping = new ProductListMapping();
  }

  public Page<ProductListDto> getProducts(Pageable pageable) {
    Page<Product> products = productRepository.findAll(pageable);
    List<ProductListDto> productsListDto = products.getContent()
        .stream()
        .map(productListMapping::mapToProductListDto)
        .toList();
    return new PageImpl<>(productsListDto, pageable, products.getTotalElements());
  }

  @Override
  public Product getProductBySlug(String slug) {
    return productRepository.findProductBySlug(slug).orElseThrow();
  }
}