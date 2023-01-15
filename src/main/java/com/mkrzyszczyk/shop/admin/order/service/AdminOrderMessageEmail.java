package com.mkrzyszczyk.shop.admin.order.service;

import com.mkrzyszczyk.shop.admin.order.model.AdminOrderStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminOrderMessageEmail {

    public static String createProcessingEmailMessage(Long orderId, AdminOrderStatus newStatus) {
        return "Your order: " + orderId + "is being processed." +
                "\nStatus has been changed to: " + newStatus.getValue() +
                "\nAfter completing it will be sent" +
                "\n\nKind regards" +
                "\nYour shop";
    }

    public static String createCompletedEmailMessage(Long orderId, AdminOrderStatus newStatus) {
        return "Your order: " + orderId + "has been completed." +
                "\nStatus has been changed to: " + newStatus.getValue() +
                "\nThank you for your purchase" +
                "\n\nKind regards" +
                "\nYour shop";
    }

    public static String createRefundEmailMessage(Long orderId, AdminOrderStatus newStatus) {
        return "Your order: " + orderId + "has been refunded." +
                "\nStatus has been changed to: " + newStatus.getValue() +
                "\n\nKind regards" +
                "\nYour shop";
    }
}