package com.company.projectmanagementdata.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidDatesProjectValidator.class)
public @interface ValidDatesProject {

    String message() default "Start date must be before end date!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
