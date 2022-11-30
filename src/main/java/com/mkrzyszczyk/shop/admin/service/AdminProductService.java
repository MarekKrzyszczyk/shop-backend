package com.mkrzyszczyk.shop.admin.service;

import com.mkrzyszczyk.shop.admin.model.AdminProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminProductService {

  Page<AdminProduct> getProducts(Pageable pageable);
}
