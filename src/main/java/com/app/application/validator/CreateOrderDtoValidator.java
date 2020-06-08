package com.app.application.validator;

import com.app.application.dto.CreateOrderDto;
import com.app.application.validator.generic.AbstractValidator;

import java.util.Map;

public class CreateOrderDtoValidator extends AbstractValidator<CreateOrderDto> {
    @Override
    public Map<String, String> validate(CreateOrderDto item) {
        if (item.getOrderIds() == null) {
            errors.put("order", "Order ids is null");
        }
        if (item.getQuantities() == null) {
            errors.put("quantity", "Quantity is null");
        }
        if (item.getQuantities().size() != item.getOrderIds().size()) {
            errors.put("List size", "quantity is not equal order ids size");
        }
        return errors;
    }
}
