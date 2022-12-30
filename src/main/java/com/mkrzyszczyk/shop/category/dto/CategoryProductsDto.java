package com.mkrzyszczyk.shop.category.dto;

import com.mkrzyszczyk.shop.common.model.Category;
import com.mkrzyszczyk.shop.common.dto.ProductListDto;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {


}
