package com.app.infrastructure.repository.impl;

import com.app.domain.product.Product;
import com.app.domain.product.ProductRepository;
import com.app.infrastructure.repository.mongo.MongoProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final MongoProductRepository mongoProductRepository;

    @Override
    public Flux<Product> findAll() {
        return mongoProductRepository.findAll();
    }

    @Override
    public Flux<Product> findAllById(List<String> ids) {
        return mongoProductRepository.findAllById(ids);
    }

    @Override
    public Mono<Product> findById(String id) {
        return mongoProductRepository.findById(id);
    }

    @Override
    public Mono<Product> addOrUpdate(Product product) {
        return mongoProductRepository.save(product);
    }

    @Override
    public Flux<Product> addAll(List<Product> items) {
        return mongoProductRepository.saveAll(items);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoProductRepository.deleteById(id);
    }
}
