package com.mkrzyszczyk.shop.admin.category.service;

import com.mkrzyszczyk.shop.admin.category.model.AdminCategory;
import com.mkrzyszczyk.shop.admin.category.model.dto.AdminCategoryDto;
import java.util.List;

public interface AdminCategoryService {

  List<AdminCategory> getCategories();

  AdminCategory getCategory(Long id);

  AdminCategory createCategory(AdminCategoryDto adminCategory);

  AdminCategory updateCategory(AdminCategoryDto adminCategoryDto, Long id);

  void deleteCategory(Long id);
}
