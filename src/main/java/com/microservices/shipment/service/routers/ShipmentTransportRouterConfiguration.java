package com.microservices.shipment.service.routers;

import com.microservices.shipment.service.handler.LoadAssignmentHandler;
import com.microservices.shipment.service.handler.ShipmentHandler;
import com.microservices.shipment.service.handler.TransportHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;


@Configuration
public class ShipmentTransportRouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> route(ShipmentHandler handler, TransportHandler transportHandler, LoadAssignmentHandler loadAssignmentHandler) {
        return RouterFunctions.route(POST("/shipment"), handler::submitRegistration)
                .andRoute(POST("/transport"), transportHandler::submitRegistration)
                .andRoute(POST("/load-assignment"), loadAssignmentHandler::distributeShipmentsToVehicles)
                .andRoute(GET("/getshipment"), loadAssignmentHandler::getAssignedShipments);

    }
}
