package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.order.model.dto.InitOrder;
import com.mkrzyszczyk.shop.order.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

  private final ShipmentRepository shipmentRepository;

  @Override
  public InitOrder getShipments() {
    return InitOrder.builder()
        .shipments(shipmentRepository.findAll())
        .build();
  }
}