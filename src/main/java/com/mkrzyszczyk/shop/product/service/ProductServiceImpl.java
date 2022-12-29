package com.mkrzyszczyk.shop.product.service;

import com.mkrzyszczyk.shop.product.model.Product;
import com.mkrzyszczyk.shop.product.model.dto.ProductListDto;
import com.mkrzyszczyk.shop.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public Page<ProductListDto> getProducts(Pageable pageable) {
    Page<Product> products = productRepository.findAll(pageable);
    List<ProductListDto> productsListDto = products.getContent()
        .stream()
        .map(this::mapToProductListDto)
        .toList();
    return new PageImpl<>(productsListDto, pageable, products.getTotalElements());
  }

  @Override
  public Product getProductBySlug(String slug) {
    return productRepository.findProductBySlug(slug).orElseThrow();
  }

  @Override
  public ProductListDto mapToProductListDto(Product product) {
    return ProductListDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .currency(product.getCurrency())
            .image(product.getImage())
            .slug(product.getSlug())
            .build();
  }
}