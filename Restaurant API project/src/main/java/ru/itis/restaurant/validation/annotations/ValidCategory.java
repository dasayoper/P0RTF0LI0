package ru.itis.restaurant.validation.annotations;

import ru.itis.restaurant.validation.validators.CategoryValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryValidator.class)
public @interface ValidCategory {
    String message() default "Category is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
