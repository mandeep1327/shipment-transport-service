package com.microservices.shipment.service.services;

import com.microservices.shipment.service.domain.Transport;
import com.microservices.shipment.service.model.TransportRegistration;
import com.microservices.shipment.service.repositories.TransportRegistrationsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransportService {

    private final TransportRegistrationsRepository transportRegistrationsRepository;

    public Mono<TransportRegistration> submitRegistration(TransportRegistration transportRegistration) {
        Transport transport = buildRequest(transportRegistration);
        return this.transportRegistrationsRepository.save(transport)
                .doOnNext(__ -> log.info("Saved Transport request"))
                .doOnError(throwable -> log.warn("Error occurred while saving to database {}", throwable.getMessage()))
                .map(this::toTransportRegistration);

    }

    public static Transport buildRequest(TransportRegistration transportRegistration) {
        return Transport.builder()
                .currentLoad(transportRegistration.getCurrentLoad())
                .dimension(transportRegistration.getDimension())
                .capacity(transportRegistration.getCapacity())
                .build();
    }

    private TransportRegistration toTransportRegistration(Transport transport) {
        return TransportRegistration.builder()
                .id(transport.getTransportId())
                .capacity(transport.getCapacity())
                .dimension(transport.getDimension())
                .build();
    }

    public Flux<Transport> getTransports() {
        return transportRegistrationsRepository.findAll()
                .switchIfEmpty(Flux.error(new Exception("NO Record Found")));

    }
}
