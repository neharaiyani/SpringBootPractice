package com.codingshots.springmvc.validation_demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * •	@interface:
 *      o	Used to create your own (custom) Java annotations.
 *      o	Annotations are defined in their own file, just like a Java class or interface.
 * •	@Constraint: Marks an annotation as being a Bean Validation constraint
 * •	@Target:
 *      o	A meta-annotation
 *      o	An annotation that can be used only for Another Annotation Definition,
 *          to define the Target or Code Elements that the Annotation can be used with.
 * •	@Retention:
 *      o	Also, a meta-annotation
 *      o	Determines whether an annotation is available at runtime.
 *      o	Indicates how long the annotated type should be kept in the program's lifecycle
 */

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // define default course code
    public String value() default "LUV";

    // define default error message
    public String message() default "must start with LUV";

    // define default groups: can group related constraints (here, no grouping, so {})
    public Class<?>[] groups() default {};

    // define default payloads, provide custom details about validation failure (severity level, error code, etc.)
    public Class<? extends Payload>[] payload() default {};
}
