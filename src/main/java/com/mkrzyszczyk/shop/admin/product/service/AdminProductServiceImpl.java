package com.mkrzyszczyk.shop.admin.product.service;

import com.mkrzyszczyk.shop.admin.common.utils.SlugifyUtils;
import com.mkrzyszczyk.shop.admin.product.model.AdminProduct;
import com.mkrzyszczyk.shop.admin.product.model.dto.AdminProductDto;
import com.mkrzyszczyk.shop.admin.product.repository.AdminProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

  public static final Long EMPTY_ID = null;
  private final AdminProductRepository adminProductRepository;

  @Override
  public Page<AdminProduct> getProducts(Pageable pageable) {
    return adminProductRepository.findAll(pageable);
  }

  @Override
  public AdminProduct getProduct(Long id) {
    return adminProductRepository.findById(id).orElseThrow();
  }

  @Override
  public AdminProduct createProduct(AdminProductDto adminProductDto) {
    return adminProductRepository.save(mapAdminProduct(adminProductDto, EMPTY_ID));
  }

  @Override
  public AdminProduct updateProduct(AdminProductDto adminProductDto, Long id) {
    return adminProductRepository.save(mapAdminProduct(adminProductDto, id));
  }

  @Override
  public void deleteProduct(Long id) {
    adminProductRepository.deleteById(id);
  }

  private AdminProduct mapAdminProduct(AdminProductDto adminProductDto, Long id) {
    return AdminProduct.builder()
        .id(id)
        .name(adminProductDto.getName())
        .description(adminProductDto.getDescription())
        .fullDescription(adminProductDto.getFullDescription())
        .categoryId(adminProductDto.getCategoryId())
        .price(adminProductDto.getPrice())
        .currency(adminProductDto.getCurrency())
        .image(adminProductDto.getImage())
        .slug(SlugifyUtils.slugify(adminProductDto.getSlug()))
        .build();
  }
}
