package com.app.domain.order_position;

import com.app.domain.order.Order;
import com.app.domain.product.Product;
import com.app.domain.value_object.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "order_position")
public class OrderPosition {
    @Id
    private String id;

    private Product product;

    private Integer quantity;

    // private Order order;

    public Money totalPrice() {
        return product.totalPrice().multiply(quantity);
    }
}
