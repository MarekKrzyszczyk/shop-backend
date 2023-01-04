package com.mkrzyszczyk.shop.product.service;

import com.mkrzyszczyk.shop.common.dto.ProductListDto;
import com.mkrzyszczyk.shop.common.mapping.ProductListMapping;
import com.mkrzyszczyk.shop.common.model.Product;
import com.mkrzyszczyk.shop.common.model.Review;
import com.mkrzyszczyk.shop.common.repository.ProductRepository;
import com.mkrzyszczyk.shop.common.repository.ReviewRepository;
import com.mkrzyszczyk.shop.product.dto.ProductDto;
import com.mkrzyszczyk.shop.product.dto.ReviewDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ReviewRepository reviewRepository;
  private final ProductListMapping productListMapping;

  public ProductServiceImpl(ProductRepository productRepository, ReviewRepository reviewRepository) {
    this.productRepository = productRepository;
    this.reviewRepository = reviewRepository;
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
  @Transactional(readOnly = true)
  public ProductDto getProductBySlug(String slug) {
    Product product = productRepository.findProductBySlug(slug).orElseThrow();
    List<Review> reviews = reviewRepository.findAllByProductIdAndModerated(product.getId(), true);
    return mapToProductDto(product, reviews);
  }

  private ProductDto mapToProductDto(Product product, List<Review> reviews) {
    return ProductDto.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .currency(product.getCurrency())
        .image(product.getImage())
        .slug(product.getSlug())
        .reviews(reviews.stream().map(review -> ReviewDto.builder()
            .id(review.getId())
            .productId(review.getProductId())
            .authorName(review.getAuthorName())
            .content(review.getContent())
            .moderate(review.isModerated())
            .build())
            .toList())
        .build();
  }
}