package com.app.application.service;

import com.app.application.dto.CreateProductDto;
import com.app.application.dto.GetProductDto;
import com.app.application.exeptions.ProductException;
import com.app.application.mapper.Mappers;
import com.app.application.validator.CreateProductDtoValidator;
import com.app.domain.product.Product;
import com.app.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Mono<String> createProduct(Mono<CreateProductDto> createProductDtoMono) {
        return createProductDtoMono
                .flatMap(createProductDto -> {
                    var createProductDtoValidator = new CreateProductDtoValidator();
                    var errors = createProductDtoValidator.validate(createProductDto);
                    if (createProductDtoValidator.hasErrors()) {
                        var errorMessage = errors
                                .entrySet()
                                .stream()
                                .map(e -> e.getKey() + ": " + e.getValue())
                                .collect(Collectors.joining(", "));
                        return Mono.error(new ProductException("Product validation error: " + errorMessage));
                    }

                    var product = Mappers.fromProductDtoToProduct(createProductDto);
                    return productRepository.addOrUpdate(product).map(Product::getId);
                });
    }
    public Flux<GetProductDto> getAllProduct(){
        return productRepository.findAll().map(Mappers::fromProductToGetProductDto);
    }
}
