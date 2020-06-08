package com.app.application.validator;

import com.app.application.dto.CreateProductDto;
import com.app.application.validator.generic.AbstractValidator;

import java.util.Map;

public class CreateProductDtoValidator extends AbstractValidator<CreateProductDto> {

    @Override
    public Map<String, String> validate(CreateProductDto createProductDto) {
        errors.clear();

        if (!isNameValid(createProductDto.getName())) {
            errors.put("name", "should contain only upper case letters and spaces");
        }

        return errors;
    }

    private boolean isNameValid(String name) {
        return name != null && name.matches("[A-Z ]+");
    }
}
