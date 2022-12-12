package com.mkrzyszczyk.shop.admin.model.dto;

import com.mkrzyszczyk.shop.admin.model.AdminProductCurrency;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AdminProductDto {

  @NotBlank(message = "name cannot be blank")
  private String name;
  @NotBlank(message = "name cannot be blank")
  private String category;
  @NotBlank(message = "name cannot be blank")
  private String description;
  @NotNull
  @Min(value = 0, message = "Price must be equal or greater than 0")
  private BigDecimal price;
  private AdminProductCurrency currency;
}
