package com.microservices.shipment.service.model;

import io.r2dbc.spi.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ShipmentDetails {

    private Integer loadId;
    private Integer shipmentId;
    private Integer transportId;

}
