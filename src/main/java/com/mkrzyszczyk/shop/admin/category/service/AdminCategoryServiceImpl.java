package com.mkrzyszczyk.shop.admin.category.service;

import static com.mkrzyszczyk.shop.admin.product.service.AdminProductServiceImpl.EMPTY_ID;

import com.github.slugify.Slugify;
import com.mkrzyszczyk.shop.admin.category.model.AdminCategory;
import com.mkrzyszczyk.shop.admin.category.model.dto.AdminCategoryDto;
import com.mkrzyszczyk.shop.admin.category.repository.AdminCategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {

  private final AdminCategoryRepository categoryRepository;

  @Override
  public List<AdminCategory> getCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public AdminCategory getCategory(Long id) {
    return categoryRepository.findById(id).orElseThrow();
  }

  @Override
  public AdminCategory createCategory(AdminCategoryDto adminCategory) {
    return categoryRepository.save(mapToAdminCategory(adminCategory, EMPTY_ID));
  }

  @Override
  public AdminCategory updateCategory(AdminCategoryDto adminCategoryDto, Long id) {
    return categoryRepository.save(mapToAdminCategory(adminCategoryDto, id));
  }

  @Override
  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }

  private AdminCategory mapToAdminCategory(AdminCategoryDto dto, Long id) {
    return AdminCategory.builder()
        .id(id)
        .name(dto.getName())
        .description(dto.getDescription())
        .slug(slugifyCategoryName(dto.getSlug()))
        .build();
  }

  private String slugifyCategoryName(String slug) {
    Slugify slugify = new Slugify();
    return slugify.withCustomReplacement("_", "-")
        .slugify(slug);
  }
}