package com.voting.service.validator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

class DateRangeValidator implements ConstraintValidator<ValidDateRange, Object> {

    private String startFieldName;
    private String endFieldName;

    @Override
    public void initialize(final ValidDateRange constraintAnnotation) {
        startFieldName = constraintAnnotation.start();
        endFieldName = constraintAnnotation.end();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object startObject = beanWrapper.getPropertyValue(startFieldName);
        Object endObject = beanWrapper.getPropertyValue(endFieldName);

        if (startObject == null || endObject == null) {
            return true;
        }
        if (!(startObject instanceof LocalDateTime) || !(endObject instanceof LocalDateTime)) {
            throw new IllegalArgumentException("Illegal method signature, expected two parameters of type LocalDateTime.");
        }
        return ((LocalDateTime) endObject).isAfter(LocalDateTime.now())
                && ((LocalDateTime) startObject).isBefore((LocalDateTime) endObject);

    }
}
