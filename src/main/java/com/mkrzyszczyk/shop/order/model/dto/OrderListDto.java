package com.mkrzyszczyk.shop.order.model.dto;

import com.mkrzyszczyk.shop.common.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderListDto {

    private Long id;
    private LocalDateTime placementDate;
    private String orderStatus;
    private BigDecimal grossValue;
}