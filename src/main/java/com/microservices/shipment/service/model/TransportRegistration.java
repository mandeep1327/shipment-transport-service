package com.microservices.shipment.service.model;

import com.microservices.shipment.service.domain.Dimension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportRegistration {
    private double capacity;
    private Dimension dimension;
    private double currentLoad;
    private Integer id;
}
