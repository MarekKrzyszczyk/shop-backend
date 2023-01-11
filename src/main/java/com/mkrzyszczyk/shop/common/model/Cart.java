package com.mkrzyszczyk.shop.common.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime created;
  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "cartId")
  private List<CartItem> items;

  public void addProduct(CartItem cartItem) {
    if (items == null) {
      items = new ArrayList<>();
    }
    items.stream()
        .filter(item -> Objects.equals(cartItem.getProduct().getId(), item.getProduct().getId()))
        .findFirst()
          .ifPresentOrElse(item -> item.setQuantity(item.getQuantity() + 1), () -> items.add(cartItem));
  }
}
