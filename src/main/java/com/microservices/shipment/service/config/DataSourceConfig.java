package com.microservices.shipment.service.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
liquibase script required jdbc datasource configration.
 */
@Configuration
@Import(DataSourceAutoConfiguration.class)
public class DataSourceConfig {
}