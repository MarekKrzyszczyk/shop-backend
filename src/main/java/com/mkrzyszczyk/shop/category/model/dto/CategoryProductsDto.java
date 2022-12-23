package com.mkrzyszczyk.shop.category.model.dto;

import com.mkrzyszczyk.shop.category.model.Category;
import com.mkrzyszczyk.shop.product.model.Product;
import org.springframework.data.domain.Page;

public record CategoryProductsDto(Category category, Page<Product> products) {


}
