package com.example.task.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueLoginConstraintValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueLogin {
    String message() default "{Login}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}