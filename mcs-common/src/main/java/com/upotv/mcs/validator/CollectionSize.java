package com.upotv.mcs.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CollectionSizeValidator.class)
public @interface CollectionSize {

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String message() default "";

	int min() default 1;
}
