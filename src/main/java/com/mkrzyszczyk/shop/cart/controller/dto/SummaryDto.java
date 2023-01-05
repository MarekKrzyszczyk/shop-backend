package com.mkrzyszczyk.shop.cart.controller.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummaryDto {

  private BigDecimal grossValue;
}
