package com.mkrzyszczyk.shop.admin.category.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdminCategoryDto {

  @NotBlank
  private String name;
  private String description;
  @NotBlank
  private String slug;
}
