package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.order.model.Shipment;
import com.mkrzyszczyk.shop.order.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

  private final ShipmentRepository shipmentRepository;

  @Override
  public List<Shipment> getShipments() {
    return shipmentRepository.findAll();
  }
}