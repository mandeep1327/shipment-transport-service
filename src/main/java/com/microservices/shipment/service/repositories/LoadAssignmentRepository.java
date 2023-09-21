package com.microservices.shipment.service.repositories;


import com.microservices.shipment.service.domain.LoadAssignment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LoadAssignmentRepository extends ReactiveCrudRepository<LoadAssignment, Integer> {

}
