package com.microservices.shipment.service.handler;

import com.microservices.shipment.service.model.ShipmentRegistration;
import com.microservices.shipment.service.services.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ShipmentHandler {

    private final ShipmentService shipmentService;

    public Mono<ServerResponse> submitRegistration(ServerRequest request) {
        var response = request.bodyToMono(ShipmentRegistration.class)
                .flatMap(shipmentService::submitRegistration);
        return ServerResponse.ok().body(response, ShipmentRegistration.class);
    }

}
