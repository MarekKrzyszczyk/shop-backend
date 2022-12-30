package com.mkrzyszczyk.shop.category.service;

import com.mkrzyszczyk.shop.category.dto.CategoryProductsDto;
import com.mkrzyszczyk.shop.category.repository.CategoryRepository;
import com.mkrzyszczyk.shop.common.dto.ProductListDto;
import com.mkrzyszczyk.shop.common.mapping.ProductListMapping;
import com.mkrzyszczyk.shop.common.model.Category;
import com.mkrzyszczyk.shop.common.model.Product;
import com.mkrzyszczyk.shop.common.repository.ProductRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final ProductListMapping productListMapping;

  public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
    this.productListMapping = new ProductListMapping();
  }

  @Override
  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public CategoryProductsDto getCategoryWithProducts(String slug, Pageable pageable) {
    Category category = categoryRepository.findBySlug(slug);
    Page<Product> products = productRepository.findByCategoryId(category.getId(), pageable);
    List<ProductListDto> productsListDto = products.getContent()
        .stream()
        .map(productListMapping::mapToProductListDto)
        .toList();
    return new CategoryProductsDto(category, new PageImpl<>(productsListDto, pageable,
        products.getTotalElements()));
  }
}