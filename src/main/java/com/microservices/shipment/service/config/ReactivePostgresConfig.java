package com.microservices.shipment.service.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ReactivePostgresConfig extends AbstractR2dbcConfiguration {

    @Value("${POSTGRES_R2DBC_URL}")
    private String url;

    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .from(ConnectionFactoryOptions.parse(url)).build());
    }

    @Bean
    @Override
    public R2dbcCustomConversions r2dbcCustomConversions() {
        return new R2dbcCustomConversions(getStoreConversions(), List.of(new DimensionToJsonConverter(), new JsonToDimensionConverter(),
                new TransportToJsonConverter(), new JsonToTransportConverter(),
                new ShipmentToJsonConverter(), new JsonToShipmentConverter()));
    }
}
