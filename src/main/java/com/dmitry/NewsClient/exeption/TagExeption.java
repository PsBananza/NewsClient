package com.dmitry.NewsClient.exeption;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TagValid.class)
public @interface TagExeption {

    String message() default ValidationConstants.TAGS_NOT_VALID;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
