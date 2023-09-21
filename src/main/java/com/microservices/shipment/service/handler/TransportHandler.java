package com.microservices.shipment.service.handler;

import com.microservices.shipment.service.model.TransportRegistration;
import com.microservices.shipment.service.services.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransportHandler {

    private final TransportService transportService;

    public Mono<ServerResponse> submitRegistration(ServerRequest request) {
        var response = request.bodyToMono(TransportRegistration.class)
                .flatMap(transportService::submitRegistration);
        return ServerResponse.ok().body(response, TransportRegistration.class);
    }

}
