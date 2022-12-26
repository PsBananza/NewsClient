package com.dmitry.NewsClient.exeption;

import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TagValid implements ConstraintValidator<TagExeption, List<String>> {
    @Override
    public void initialize(TagExeption constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            return false;
        }

        for (String current : value) {
            if (Collections.frequency(value, current) > 1) {
                return false;
            }
            if (current.length() < 3
                    || current.length() > 25
                    || current.isBlank()) {
                return false;
            }
        }
        return true;
    }
}
