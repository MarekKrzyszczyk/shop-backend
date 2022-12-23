package com.mkrzyszczyk.shop.category.service;

import com.mkrzyszczyk.shop.category.model.Category;
import com.mkrzyszczyk.shop.category.model.dto.CategoryProductsDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

  List<Category> getCategories();

  CategoryProductsDto getCategoryWithProducts(String slug, Pageable pageable);
}
