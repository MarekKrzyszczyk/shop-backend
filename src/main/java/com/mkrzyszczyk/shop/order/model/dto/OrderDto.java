package com.mkrzyszczyk.shop.order.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderDto {
  @NotBlank
  private String firstname;
  @NotBlank
  private String lastname;
  @NotBlank
  private String street;
  @NotBlank
  private String zipcode;
  @NotBlank
  private String city;
  @NotBlank
  @Email
  private String email;
  @NotBlank
  private String phone;
  @NotNull
  private Long cartId;
  private Long shipmentId;
}
