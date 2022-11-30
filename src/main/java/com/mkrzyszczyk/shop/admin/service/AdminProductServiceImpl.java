package com.mkrzyszczyk.shop.admin.service;

import com.mkrzyszczyk.shop.admin.model.AdminProduct;
import com.mkrzyszczyk.shop.admin.repository.AdminProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

  private final AdminProductRepository adminProductRepository;

  @Override
  public Page<AdminProduct> getProducts(Pageable pageable) {
    return adminProductRepository.findAll(pageable);
  }
}
