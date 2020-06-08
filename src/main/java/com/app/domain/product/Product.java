package com.app.domain.product;

import com.app.domain.exception.ProductDomainException;
import com.app.domain.value_object.Discount;
import com.app.domain.value_object.Money;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String name;
    private Money price;
    private Discount discount;

    public Money totalPrice() {
        return price.multiply(discount.reverse().toString());
    }

    public void changeDiscount(String newDiscount) {
        if (Objects.isNull(newDiscount)) {
            throw new ProductDomainException("new discount value object is null");
        }
        this.discount = new Discount(newDiscount);
    }
}
