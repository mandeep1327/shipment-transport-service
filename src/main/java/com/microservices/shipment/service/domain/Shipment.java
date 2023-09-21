package com.microservices.shipment.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "Shipment")
public class Shipment implements Persistable<Integer> {

    @Id
    @Column("id")
    private Integer shipmentId;
    @Column("dimension")
    private Dimension dimension;
    @Column("source")
    private String source;
    @Column("destination")
    private String destination;
    @Column("weight")
    private double weight;
    @Column("shipment_date")
    private Instant shipmentDate;

    @Override
    @Transient
    public boolean isNew() {
        return shipmentId==null;
    }

    @Override
    public Integer getId() {
        return this.shipmentId;
    }

}
