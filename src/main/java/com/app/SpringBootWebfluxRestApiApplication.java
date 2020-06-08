package com.app;

import com.app.domain.order.Order;
import com.app.domain.order.OrderRepository;
import com.app.domain.order_position.OrderPosition;
import com.app.domain.order_position.OrderPositionRepository;
import com.app.domain.product.Product;
import com.app.domain.product.ProductRepository;
import com.app.domain.value_object.Discount;
import com.app.domain.value_object.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootWebfluxRestApiApplication {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderPositionRepository orderPositionRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxRestApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            /*productRepository
                    .addOrUpdate(Product
                            .builder()
                            .name("PR2")
                            .price(new Money("45"))
                            .discount(new Discount("0.6"))
                            .build())
                    .subscribe(System.out::print);*/

            List<String> ids = List.of("5eb31bc9614baa07f964efb9", "5eb3225c0a82f554f3d3bb44");
            List<Integer> quantities = List.of(10, 20);

            Flux<OrderPosition> orderPositionFlux = productRepository
                    .findAllById(ids)
                    .collectList()
                    .flatMapMany(products -> {
                                var orderPositions = IntStream
                                        .range(0, quantities.size())
                                        .boxed()
                                        .map(index -> OrderPosition
                                                .builder()
                                                .product(products.get(index))
                                                .quantity(quantities.get(index))
                                                .build())
                                        .collect(Collectors.toList());
                                return orderPositionRepository.addAll(orderPositions);
                            }
                    );
            orderPositionFlux.subscribe(System.out::println);


        };
    }

}
