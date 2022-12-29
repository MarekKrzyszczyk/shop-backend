package com.mkrzyszczyk.shop.product.model;

import com.mkrzyszczyk.shop.review.model.Review;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Long categoryId;
  private String description;
  private BigDecimal price;
  private String currency;
  private String image;
  private String slug;
  @OneToMany
  @JoinColumn(name = "productId")
  private List<Review> reviews;
}
