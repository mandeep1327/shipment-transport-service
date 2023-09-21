package com.microservices.shipment.service.repositories;


import com.microservices.shipment.service.domain.Transport;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransportRegistrationsRepository extends ReactiveCrudRepository<Transport, Integer> {
}
