package com.mkrzyszczyk.shop.admin.order.controller.dto;

import com.mkrzyszczyk.shop.common.model.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AdminOrderDto {

    private Long id;
    private LocalDateTime placementDate;
    private OrderStatus orderStatus;
    private BigDecimal grossValue;
}