package com.app.infrastructure.routing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routing {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(RoutingHandlers routingHandlers) {
        return RouterFunctions
                .nest(
                        path("/products"),
                        route(GET("").and(accept(MediaType.APPLICATION_JSON)), routingHandlers::getProduct)
                        .andRoute(POST("").and(accept(MediaType.APPLICATION_JSON)), routingHandlers::createProduct))
                .andNest(
                        path("/orders"),
                        route(GET("").and(accept(MediaType.APPLICATION_JSON)), routingHandlers::getOrders)
                        .andRoute(POST("").and(accept(MediaType.APPLICATION_JSON)), routingHandlers::createOrder));
    }
}
