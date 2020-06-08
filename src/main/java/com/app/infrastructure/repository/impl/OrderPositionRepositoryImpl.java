package com.app.infrastructure.repository.impl;

import com.app.domain.order_position.OrderPosition;
import com.app.domain.order_position.OrderPositionRepository;
import com.app.infrastructure.repository.mongo.MongoOrderPositionRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class OrderPositionRepositoryImpl implements OrderPositionRepository {
    private MongoOrderPositionRepository mongoOrderPositionRepository;

    public OrderPositionRepositoryImpl(MongoOrderPositionRepository mongoOrderPositionRepository) {
        this.mongoOrderPositionRepository = mongoOrderPositionRepository;
    }


    @Override
    public Flux<OrderPosition> findAll() {
        return mongoOrderPositionRepository.findAll();
    }

    @Override
    public Flux<OrderPosition> findAllById(List<String> strings) {
        return mongoOrderPositionRepository.findAllById(strings);
    }

    @Override
    public Mono<OrderPosition> findById(String s) {
        return mongoOrderPositionRepository.findById(s);
    }

    @Override
    public Mono<OrderPosition> addOrUpdate(OrderPosition orderPosition) {
        return mongoOrderPositionRepository.save(orderPosition);
    }

    @Override
    public Flux<OrderPosition> addAll(List<OrderPosition> items) {
        return mongoOrderPositionRepository.saveAll(items);
    }

    @Override
    public Mono<Void> deleteById(String s) {
        return mongoOrderPositionRepository.deleteById(s);
    }
}
