package com.microservices.shipment.service.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.microservices.shipment.service.domain.Shipment;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
@Slf4j
public class ShipmentToJsonConverter implements Converter<Shipment, Json> {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public Json convert(Shipment source) {
        try {
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return Json.of(objectMapper.writeValueAsString(source));

        } catch (JsonProcessingException e) {
            log.error("Error occurred while serializing map to JSON:{}", source, e);
        }
        return Json.of("");
    }
}
