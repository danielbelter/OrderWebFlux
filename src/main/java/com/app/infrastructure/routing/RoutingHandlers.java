package com.app.infrastructure.routing;

import com.app.application.dto.CreateOrderDto;
import com.app.application.dto.CreateProductDto;
import com.app.application.dto.GetProductDto;
import com.app.application.service.OrderService;
import com.app.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RoutingHandlers {
    private final ProductService productService;
    private final OrderService orderService;

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        Mono<CreateProductDto> createProductDtoMono = serverRequest.bodyToMono(CreateProductDto.class);
        return productService
                .createProduct(createProductDtoMono)
                .flatMap(productId -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(productId)))
                .onErrorResume(error -> ServerResponse
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(error.getMessage())));
    }

    public Mono<ServerResponse> getProduct(ServerRequest serverRequest) {
        return productService
                .getAllProduct()
                .collectList()
                .flatMap(products -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(products)))
                .onErrorResume(error -> ServerResponse
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(error.getMessage())));
    }

    public Mono<ServerResponse> createOrder(ServerRequest serverRequest) {
        Mono<CreateOrderDto> createOrderDtoMono = serverRequest.bodyToMono(CreateOrderDto.class);
        return orderService
                .createOrder(createOrderDtoMono)
                .flatMap(order -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(order)))
                .onErrorResume(error -> ServerResponse
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(error.getMessage())));

    }

    public Mono<ServerResponse> getOrders(ServerRequest serverRequest) {
        return orderService
                .getOrderDto()
                .collectList()
                .flatMap(order -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(order)))
                .onErrorResume(error -> ServerResponse
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(error.getMessage())));
    }
}
