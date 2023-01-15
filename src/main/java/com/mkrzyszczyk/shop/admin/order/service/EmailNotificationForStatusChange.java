package com.mkrzyszczyk.shop.admin.order.service;

import com.mkrzyszczyk.shop.admin.order.model.AdminOrder;
import com.mkrzyszczyk.shop.admin.order.model.AdminOrderStatus;
import com.mkrzyszczyk.shop.common.mail.EmailClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EmailNotificationForStatusChange {

    private final EmailClientService emailClientService;

    public void sendEmailNotification(AdminOrderStatus newStatus, AdminOrder adminOrder) {
        if (newStatus == AdminOrderStatus.PROCESSING) {
            sendEmail(adminOrder.getEmail(),
                    "Order " + adminOrder.getId() + " changed status to: " + newStatus.getValue(),
                    AdminOrderMessageEmail.createProcessingEmailMessage(adminOrder.getId(), newStatus));
        } else if (newStatus.equals(AdminOrderStatus.COMPLETED)) {
            sendEmail(adminOrder.getEmail(),
                    "Order " + adminOrder.getId() + " has been completed",
                    AdminOrderMessageEmail.createCompletedEmailMessage(adminOrder.getId(), newStatus));
        } else if (newStatus == AdminOrderStatus.REFUND) {
            sendEmail(adminOrder.getEmail(),
                    "Money for Order " + adminOrder.getId() + " has been refunded",
                    AdminOrderMessageEmail.createRefundEmailMessage(adminOrder.getId(), newStatus));
        }
    }

    private void sendEmail(String email, String subject, String content) {
        emailClientService.getInstance().send(email, subject, content);
    }
}
