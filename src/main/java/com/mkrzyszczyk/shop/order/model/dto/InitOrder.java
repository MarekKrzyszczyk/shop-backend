package com.mkrzyszczyk.shop.order.model.dto;

import com.mkrzyszczyk.shop.order.model.Shipment;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InitOrder {

  private List<Shipment> shipments;
}