package ru.itis.restaurant.validation.annotations;

import ru.itis.restaurant.validation.validators.DataValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataValidator.class)
public @interface ValidData {
    String message() default "Data is invalid";

    String[] fields() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
