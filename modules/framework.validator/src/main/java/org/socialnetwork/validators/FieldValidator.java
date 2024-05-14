package org.socialnetwork.validators;

import org.socialnetwork.exceptions.ValidationException;

import java.lang.reflect.Field;

public interface FieldValidator {
    boolean supports(Field field);
    // TODO: Need to create specific exception instead of IllegalArgument
    void validate(Field field, Object object) throws IllegalAccessException, ValidationException;
}
