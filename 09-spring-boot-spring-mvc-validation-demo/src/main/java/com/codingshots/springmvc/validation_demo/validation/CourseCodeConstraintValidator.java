package com.codingshots.springmvc.validation_demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
        coursePrefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String formCode, ConstraintValidatorContext context) {

        // any custom business logic here
        boolean result;

        // check for null
        if(formCode != null)
            result = formCode.startsWith(coursePrefix);
        else
            result = true;

        return result;
    }
}
