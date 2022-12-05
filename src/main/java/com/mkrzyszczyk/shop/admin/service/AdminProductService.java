package com.mkrzyszczyk.shop.admin.service;

import com.mkrzyszczyk.shop.admin.model.AdminProduct;
import com.mkrzyszczyk.shop.admin.model.dto.AdminProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminProductService {

  Page<AdminProduct> getProducts(Pageable pageable);

  AdminProduct getProduct(Long id);

  AdminProduct createProduct(AdminProductDto adminProductDto);

  AdminProduct updateProduct(AdminProductDto adminProductDto, Long id);
}
