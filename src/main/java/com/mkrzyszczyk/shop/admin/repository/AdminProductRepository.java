package com.mkrzyszczyk.shop.admin.repository;

import com.mkrzyszczyk.shop.admin.model.AdminProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {

}
