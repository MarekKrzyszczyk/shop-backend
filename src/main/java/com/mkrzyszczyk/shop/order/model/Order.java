package com.mkrzyszczyk.shop.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`order`")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime placementDate;
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
  @OneToMany
  @JoinColumn(name = "orderId")
  private List<OrderRow> orderRows;
  private BigDecimal grossValue;
  private String firstname;
  private String lastname;
  private String street;
  private String zipcode;
  private String city;
  private String email;
  private String phone;
}