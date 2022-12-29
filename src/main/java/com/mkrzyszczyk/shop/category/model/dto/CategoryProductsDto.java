package com.mkrzyszczyk.shop.category.model.dto;

import com.mkrzyszczyk.shop.category.model.Category;
import com.mkrzyszczyk.shop.product.model.dto.ProductListDto;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {


}
