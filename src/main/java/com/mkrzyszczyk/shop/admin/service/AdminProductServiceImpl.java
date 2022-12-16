package com.mkrzyszczyk.shop.admin.service;

import com.github.slugify.Slugify;
import com.mkrzyszczyk.shop.admin.model.AdminProduct;
import com.mkrzyszczyk.shop.admin.model.dto.AdminProductDto;
import com.mkrzyszczyk.shop.admin.repository.AdminProductRepository;
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
        .category(adminProductDto.getCategory())
        .price(adminProductDto.getPrice())
        .currency(adminProductDto.getCurrency())
        .image(adminProductDto.getImage())
        .slug(slugify(adminProductDto.getSlug()))
        .build();
  }

  private String slugify(String slug) {
    Slugify slugify = new Slugify();
    return slugify.withCustomReplacement("_", "-")
        .slugify(slug);
  }
}
