package com.app.infrastructure.repository.mongo;

import com.app.domain.product.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoProductRepository extends ReactiveMongoRepository<Product, String> {
}
