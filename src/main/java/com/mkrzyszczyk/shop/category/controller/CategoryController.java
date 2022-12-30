package com.mkrzyszczyk.shop.category.controller;

import com.mkrzyszczyk.shop.common.model.Category;
import com.mkrzyszczyk.shop.category.dto.CategoryProductsDto;
import com.mkrzyszczyk.shop.category.service.CategoryService;
import java.util.List;
import javax.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.ok(categoryService.getCategories());
  }

  @GetMapping("/{slug}/products")
  public ResponseEntity<CategoryProductsDto> getCategoryWithProducts(@PathVariable @Pattern(regexp = "[a-z0-9\\-]+")
      @Length(max = 255) String slug, Pageable pageableh) {
    return ResponseEntity.ok(categoryService.getCategoryWithProducts(slug, pageableh));
  }
}
