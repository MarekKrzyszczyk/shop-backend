package com.mkrzyszczyk.shop.admin.category.controller;

import com.mkrzyszczyk.shop.admin.category.model.AdminCategory;
import com.mkrzyszczyk.shop.admin.category.model.dto.AdminCategoryDto;
import com.mkrzyszczyk.shop.admin.category.service.AdminCategoryService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

  private final AdminCategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<AdminCategory>> getCategories() {
    return ResponseEntity.ok(categoryService.getCategories());
  }

  @GetMapping("/{id}")
  public ResponseEntity<AdminCategory> getCategory(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.getCategory(id));
  }

  @PostMapping
  public ResponseEntity<AdminCategory> createCategory(@RequestBody @Valid AdminCategoryDto adminCategory) {
    return ResponseEntity.ok(categoryService.createCategory(adminCategory));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AdminCategory> updateCategory(@RequestBody @Valid AdminCategoryDto adminProductDto,
      @PathVariable Long id) {
    return ResponseEntity.ok(categoryService.updateCategory(adminProductDto, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AdminCategory> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.ok().build();
  }

}
