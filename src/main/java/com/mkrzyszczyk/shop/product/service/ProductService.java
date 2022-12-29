package com.mkrzyszczyk.shop.product.service;

import com.mkrzyszczyk.shop.product.model.Product;
import com.mkrzyszczyk.shop.product.model.dto.ProductListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  Page<ProductListDto> getProducts(Pageable pageable);

  Product getProductBySlug(String slug);

  ProductListDto mapToProductListDto(Product product);
}