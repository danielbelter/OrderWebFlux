package com.app.infrastructure.repository.mongo;

import com.app.domain.order.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoOrderRepository extends ReactiveMongoRepository<Order, String> {
}
