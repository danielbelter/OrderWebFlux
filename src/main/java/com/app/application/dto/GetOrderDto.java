package com.app.application.dto;

import com.app.domain.value_object.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetOrderDto {
    private String id;
    private Money totalPrice;

}
