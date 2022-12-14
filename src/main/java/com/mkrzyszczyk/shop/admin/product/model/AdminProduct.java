package com.mkrzyszczyk.shop.admin.product.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class AdminProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Long categoryId;
  private String description;
  private BigDecimal price;
  @Enumerated(EnumType.STRING)
  private AdminProductCurrency currency;
  private String image;
  private String slug;
  @Column(name = "full_description")
  private String fullDescription;
}