package com.mkrzyszczyk.shop.order.service.mapper;

import com.mkrzyszczyk.shop.order.model.Order;

import java.time.format.DateTimeFormatter;

public class OrderEmailMessageMapper {

    public static String createEmailMessage(Order order) {
        return "Your order nr: " + order.getId() +
                "\nPlacement date: " + order.getPlacementDate().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm")) +
                "\nAmount: " + order.getGrossValue() + " PLN" +
                "\n\n" +
                "\nPayment: " + order.getPayment().getName() + (order.getPayment().getNote() != null ?
                "\n" + order.getPayment().getNote() : "") +
                "\n\nThank you for your order!";
    }
}