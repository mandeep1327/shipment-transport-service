package com.microservices.shipment.service.services;

import com.microservices.shipment.service.domain.Dimension;
import com.microservices.shipment.service.domain.LoadAssignment;
import com.microservices.shipment.service.domain.Transport;
import com.microservices.shipment.service.exceptions.DatabaseException;
import com.microservices.shipment.service.repositories.LoadAssignmentRepository;
import com.microservices.shipment.service.repositories.TransportRegistrationsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadingService {
    private final TransportService availableTransports;
    private final ShipmentService unassignedShipment;
    private final LoadAssignmentRepository loadAssignmentRepository;
    private final TransportRegistrationsRepository transportRepository;

    public Mono<String> asignmentShipment() {
        Mono<List<Transport>> sortedTransportList = null;

        return unassignedShipment.getShipments()
                .flatMap(shipment -> Optional.ofNullable(sortedTransportList)
                        .orElse(getSortedTransportList())
                        .map(transport -> transport.stream()

                                .filter(tr -> (tr.getCapacity() - tr.getCurrentLoad()) >= shipment.getWeight())
                                .filter(dm -> getVolume(dm.getDimension()) >= getVolume(shipment.getDimension()))
                                .findFirst())
                        .filter(tr -> tr.get().getCurrentLoad() < tr.get().getCapacity()) //prevent overloading
                        .map(transport -> LoadAssignment.builder()
                                .shipment(shipment)
                                .transport(transport.get())
                                .build())


                )
                .doOnNext(la -> log.info("LoadAssignment {}", la))
                .flatMap(loadAssignmentRepository::save)
                .flatMap(this::updateTransport)
                .next();
    }

    private Mono<List<Transport>> getSortedTransportList() {
        return availableTransports.getTransports()
                .doOnNext(transport -> log.info("assigned list {}", transport))
                .collectSortedList(Comparator.comparingDouble(Transport::getCapacity))
                .switchIfEmpty(Mono.error(new Exception("No Record")));
    }

    private double getVolume(Dimension dimension) {
        return dimension.getHeight() * dimension.getWidth() * dimension.getLength();
    }

    public Mono<String> updateTransport(LoadAssignment loadAssignment) {
        return Optional.ofNullable(loadAssignment)
                .map(la -> {
                    la.getTransport().setCurrentLoad(la.getTransport().getCurrentLoad() + la.getShipment().getWeight());
                    return la.getTransport();
                })
                .map(transportRepository::save)
                .orElseThrow(() -> new DatabaseException("Exception occurred for key during database update:"))
                .switchIfEmpty(Mono.error(new DatabaseException("No data found for the key")))
                .then(Mono.just("Successfully assigned the shipment"));
    }

    public Flux<LoadAssignment> getAssignedShipments() {
        return loadAssignmentRepository.findAll()
                .switchIfEmpty(Flux.error(new Exception("NO Record Found")));
    }

}
