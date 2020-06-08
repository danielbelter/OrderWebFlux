package com.app.domain.order;

import com.app.domain.order_position.OrderPosition;
import com.app.domain.product.Product;
import com.app.domain.value_object.Money;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private LocalDate date;

    @DBRef(db = "test_db", lazy = true)
    private List<OrderPosition> orderPositions;

    public Money totalPrice() {
        return orderPositions
                .stream()
                .map(OrderPosition::totalPrice)
                .reduce(new Money(), Money::add);
    }
}
