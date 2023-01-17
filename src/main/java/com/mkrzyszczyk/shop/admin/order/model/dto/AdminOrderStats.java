package com.mkrzyszczyk.shop.admin.order.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class AdminOrderStats {
    private List<Integer> labels;
    private List<Long> orders;
    private List<BigDecimal> sales;
}