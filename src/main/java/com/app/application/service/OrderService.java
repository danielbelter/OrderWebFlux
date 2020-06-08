package com.app.application.service;

import com.app.application.dto.CreateOrderDto;
import com.app.application.dto.CreateProductDto;
import com.app.application.dto.GetOrderDto;
import com.app.application.exeptions.ProductException;
import com.app.application.mapper.Mappers;
import com.app.application.validator.CreateOrderDtoValidator;
import com.app.domain.order.Order;
import com.app.domain.order.OrderRepository;
import com.app.domain.order_position.OrderPosition;
import com.app.domain.order_position.OrderPositionRepository;
import com.app.domain.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderPositionRepository orderPositionRepository;

    public Mono<String> createOrder(Mono<CreateOrderDto> createOrderDto) {
        return createOrderDto.flatMap(createOrder -> {
            var validator = new CreateOrderDtoValidator();
            var errors = validator.validate(createOrder);
            if (validator.hasErrors()) {
                var errorMessage = errors
                        .entrySet()
                        .stream()
                        .map(e -> e.getKey() + ": " + e.getValue())
                        .collect(Collectors.joining(", "));
                return Mono.error(new ProductException("Order validation error: " + errorMessage));
            }

            AtomicInteger idx = new AtomicInteger();
            return productRepository
                    .findAllById(createOrder.getOrderIds())
                    .map(product -> OrderPosition
                            .builder()
                            .product(product)
                            .quantity(createOrder.getQuantities().get(idx.getAndIncrement()))
                            .build())
                    .collectList()
                    .flatMapMany(orderPositions -> orderPositionRepository.addAll(orderPositions))
                    .collectList()
                    .flatMap(orderPositions -> orderRepository.addOrUpdate(Order
                            .builder()
                            .date(LocalDate.now())
                            .orderPositions(orderPositions)
                            .build())
                            .map(Order::getId));
        });
    }

    public Flux<GetOrderDto> getOrderDto() {
        return orderRepository.findAll().map(Mappers::fromOrderToGetOrderDto);
    }

}
