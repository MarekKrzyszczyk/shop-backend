package com.mkrzyszczyk.shop.product.service;

import com.mkrzyszczyk.shop.common.model.Product;
import com.mkrzyszczyk.shop.common.dto.ProductListDto;
import com.mkrzyszczyk.shop.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  Page<ProductListDto> getProducts(Pageable pageable);

  ProductDto getProductBySlug(String slug);
}