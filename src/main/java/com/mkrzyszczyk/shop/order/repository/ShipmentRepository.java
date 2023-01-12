package com.mkrzyszczyk.shop.order.repository;

import com.mkrzyszczyk.shop.order.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

}