package com.mkrzyszczyk.shop.product.repository;

import com.mkrzyszczyk.shop.product.model.Product;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findProductBySlug(String slug);

  Page<Product> findBycategoryId(Long id, Pageable pageable);
}