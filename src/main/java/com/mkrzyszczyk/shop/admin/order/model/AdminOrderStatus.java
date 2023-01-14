package com.mkrzyszczyk.shop.admin.order.model;

import lombok.Getter;

@Getter
public enum AdminOrderStatus {
    NEW("NEW"),
    PAID("PAID"),
    PROCESSING("IN PROGRESS"),
    WAITING_FOR_DELIVERY("READY"),
    COMPLETED("DONE"),
    CANCELED("CANCELED"),
    REFUND("REFUNDED");

    private String value;

    AdminOrderStatus(String value) {
        this.value = value;
    }
}
