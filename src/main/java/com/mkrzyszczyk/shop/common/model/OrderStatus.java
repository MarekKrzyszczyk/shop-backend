package com.mkrzyszczyk.shop.common.model;

public enum OrderStatus {
  NEW("NEW"),
  PAID("PAID"),
  PROCESSING("IN PROGRESS"),
  WAITING_FOR_DELIVERY("READY"),
  COMPLETED("DONE"),
  CANCELED("CANCELED"),
  REFUND("REFUNDED");

  private String value;

  OrderStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
