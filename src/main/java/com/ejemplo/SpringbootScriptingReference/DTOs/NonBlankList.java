package com.ejemplo.SpringbootScriptingReference.DTOs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//Archivo DTO de validacion personalizada para una propiedad, en este caso un array opcional de strings
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NonBlankList.Validator.class)
@Documented
public @interface NonBlankList {

    String message() default "Each element must be a non‑blank string";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements jakarta.validation.ConstraintValidator<NonBlankList, List<String>> {

        @Override
        public void initialize(NonBlankList constraintAnnotation) {

        }

        @Override
        public boolean isValid(List<String> value, jakarta.validation.ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            if (value.isEmpty()) {
                return false;
            }
            for (String s : value) {
                if (s == null || s.trim().isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }
}
