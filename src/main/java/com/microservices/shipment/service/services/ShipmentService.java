package com.microservices.shipment.service.services;

import com.microservices.shipment.service.domain.Shipment;
import com.microservices.shipment.service.model.ShipmentRegistration;
import com.microservices.shipment.service.repositories.ShipmentRegistrationsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;


@Slf4j
@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRegistrationsRepository shipmentRegistrationsRepository;

    public Mono<ShipmentRegistration> submitRegistration(ShipmentRegistration shipmentRegistration) {
        Shipment buildShipmentRequest = buildRequest(shipmentRegistration);
        return this.shipmentRegistrationsRepository.save(buildShipmentRequest)
                .doOnNext(__ -> log.info("Saved Shipment request"))
                .doOnError(throwable -> log.warn("Error occurred while saving to database {}", throwable.getMessage()))
                .map(this::toShipmentRegistration);

    }

    public static Shipment buildRequest(ShipmentRegistration shipmentRegistration) {
        return Shipment.builder()
                .source(shipmentRegistration.getSource())
                .shipmentDate(Instant.now())
                .dimension(shipmentRegistration.getDimension())
                .weight(shipmentRegistration.getWeight())
                .destination(shipmentRegistration.getDestination())
                .build();
    }

    private ShipmentRegistration toShipmentRegistration(Shipment shipment) {
        return ShipmentRegistration.builder()
                .source(shipment.getSource())
                .destination(shipment.getDestination())
                .shipmentDate(shipment.getShipmentDate())
                .dimension(shipment.getDimension())
                .weight(shipment.getWeight())
                .id(shipment.getShipmentId())
                .build();
    }

    public Flux<Shipment> getShipments() {
        return this.shipmentRegistrationsRepository.findAll()
                .switchIfEmpty(Flux.error(new Exception("NO Record Found")));

    }
}
