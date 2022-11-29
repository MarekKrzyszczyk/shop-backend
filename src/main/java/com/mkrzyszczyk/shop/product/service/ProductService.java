package com.mkrzyszczyk.shop.product.service;

import com.mkrzyszczyk.shop.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  Page<Product> getProducts(Pageable pageable);

}