package com.microservices.shipment.service.repositories;


import com.microservices.shipment.service.domain.Shipment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ShipmentRegistrationsRepository extends ReactiveCrudRepository<Shipment, Integer> {

}
