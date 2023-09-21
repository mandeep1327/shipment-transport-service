package com.microservices.shipment.service.handler;

import com.microservices.shipment.service.domain.LoadAssignment;
import com.microservices.shipment.service.model.ShipmentDetails;
import com.microservices.shipment.service.services.LoadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LoadAssignmentHandler {

    private final LoadingService loadingService;

    public Mono<ServerResponse> distributeShipmentsToVehicles(ServerRequest request) {
        var response = Mono.just(request)
                .flatMap(serverRequest -> loadingService.asignmentShipment());
        return ServerResponse.ok().body(response, String.class);
    }

    public Mono<ServerResponse> getAssignedShipments(ServerRequest request) {
        var response = Mono.just(request)
                .flatMapMany(req -> loadingService.getAssignedShipments())
                .map(this::toShipmentDetails);
        return ServerResponse.ok().body(response, ShipmentDetails.class);
    }

    private ShipmentDetails toShipmentDetails(LoadAssignment loadAssignment) {
        return ShipmentDetails.builder().loadId(loadAssignment.getLoadId())
                .shipmentId(loadAssignment.getShipment().getShipmentId())
                .transportId(loadAssignment.getTransport().getTransportId()).build();
    }
}
