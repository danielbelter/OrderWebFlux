package com.app.application.mapper;

import com.app.application.dto.CreateProductDto;
import com.app.application.dto.GetOrderDto;
import com.app.application.dto.GetProductDto;
import com.app.domain.order.Order;
import com.app.domain.product.Product;
import com.app.domain.value_object.Discount;
import com.app.domain.value_object.Money;

public interface Mappers {
    static Product fromProductDtoToProduct(CreateProductDto createProductDto) {
        return Product.builder()
                .discount(new Discount(createProductDto.getDiscount()))
                .price(new Money(createProductDto.getPrice()))
                .name(createProductDto.getName())
                .build();

    }

    static GetProductDto fromProductToGetProductDto(Product product) {
        return GetProductDto.builder()
                .name(product.getName())
                .totalPrice(product.totalPrice().toString())
                .build();
    }


    static GetOrderDto fromOrderToGetOrderDto(Order order) {
        return GetOrderDto.builder()
                .id(order.getId())
                .totalPrice(order.totalPrice())
                .build();
    }
}
