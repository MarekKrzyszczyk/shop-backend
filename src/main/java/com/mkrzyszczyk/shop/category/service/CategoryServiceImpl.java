package com.mkrzyszczyk.shop.category.service;

import com.mkrzyszczyk.shop.category.model.Category;
import com.mkrzyszczyk.shop.category.model.dto.CategoryProductsDto;
import com.mkrzyszczyk.shop.category.repository.CategoryRepository;
import com.mkrzyszczyk.shop.product.model.Product;
import com.mkrzyszczyk.shop.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  @Override
  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public CategoryProductsDto getCategoryWithProducts(String slug, Pageable pageable) {
    Category category = categoryRepository.findBySlug(slug);
    Page<Product> products = productRepository.findBycategoryId(category.getId(), pageable);
    return new CategoryProductsDto(category, products);
  }
}