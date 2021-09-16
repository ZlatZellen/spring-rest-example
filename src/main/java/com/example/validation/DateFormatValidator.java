package com.example.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {
    private DateTimeFormatter formatter;

    @Override
    public void initialize(DateFormat constraintAnnotation) {
        formatter = DateTimeFormatter.ofPattern(constraintAnnotation.pattern());
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
