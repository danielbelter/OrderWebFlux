package com.app.infrastructure.repository.impl;

import com.app.domain.generic.CrudRepository;
import com.app.domain.order.Order;
import com.app.domain.order.OrderRepository;
import com.app.infrastructure.repository.mongo.MongoOrderRepository;
import com.app.infrastructure.repository.mongo.MongoProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final MongoOrderRepository orderRepository;

    @Override
    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Flux<Order> findAllById(List<String> ids) {
        return orderRepository.findAllById(ids);
    }

    @Override
    public Mono<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Mono<Order> addOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Flux<Order> addAll(List<Order> items) {
        return orderRepository.saveAll(items);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return orderRepository.deleteById(id);
    }
}
