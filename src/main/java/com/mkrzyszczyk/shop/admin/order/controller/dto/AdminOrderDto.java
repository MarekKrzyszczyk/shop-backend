package com.mkrzyszczyk.shop.admin.order.controller.dto;

import com.mkrzyszczyk.shop.admin.order.model.AdminOrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AdminOrderDto {

    private Long id;
    private LocalDateTime placementDate;
    private AdminOrderStatus orderStatus;
    private BigDecimal grossValue;
}