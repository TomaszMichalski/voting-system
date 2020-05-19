package com.voting.service.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDateTime;

@Constraint(validatedBy = ConsistentDateParameterValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidDateRange {

    @SuppressWarnings("unused")
    String message() default "End date must be after begin date and both must be in the future";

    @SuppressWarnings("unused")
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String start();

    String end();
}

class ConsistentDateParameterValidator
        implements ConstraintValidator<ValidDateRange, Object> {

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
        return ((LocalDateTime) startObject).isAfter(LocalDateTime.now())
                && ((LocalDateTime) startObject).isBefore((LocalDateTime) endObject);

    }
}
