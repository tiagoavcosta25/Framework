package org.socialnetwork;

import org.socialnetwork.validators.FieldValidator;
import org.socialnetwork.validators.NotBlankValidator;
import org.socialnetwork.validators.SizeValidator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    private static final List<FieldValidator> validators = new ArrayList<>();

    static {
        validators.add(new NotBlankValidator());
        validators.add(new SizeValidator());
    }

    public static void validate(Object object) throws IllegalAccessException, IllegalArgumentException {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            for (FieldValidator validator : validators) {
                if (validator.supports(field)) {
                    validator.validate(field, object);
                }
            }
        }
    }
}
