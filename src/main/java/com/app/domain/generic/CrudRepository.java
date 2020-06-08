package com.app.domain.generic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CrudRepository<T, ID> {
    Flux<T> findAll();
    Flux<T> findAllById(List<ID> ids);
    Mono<T> findById(ID id);
    Mono<T> addOrUpdate(T t);
    Flux<T> addAll(List<T> items);
    Mono<Void> deleteById(ID id);
}
