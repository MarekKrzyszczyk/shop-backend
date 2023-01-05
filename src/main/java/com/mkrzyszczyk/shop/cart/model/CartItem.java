package com.mkrzyszczyk.shop.cart.model;

import com.mkrzyszczyk.shop.common.model.Product;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  private Product product;
  private int quantity;
  private Long cartId;

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
