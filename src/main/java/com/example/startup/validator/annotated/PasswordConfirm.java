package com.example.startup.validator.annotated;

import com.example.startup.validator.PasswordConfirmValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmValidator.class)
public @interface PasswordConfirm {
    String message() default "RePassword isn't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
