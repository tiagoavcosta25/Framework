package org.socialnetwork.validators;

import org.socialnetwork.annotations.NotBlank;
import org.socialnetwork.exceptions.NotBlankException;

import java.lang.reflect.Field;
public class NotBlankValidator implements FieldValidator {
    @Override
    public boolean supports(Field field) {
        return field.isAnnotationPresent(NotBlank.class);
    }

    @Override
    public void validate(Field field, Object object) throws IllegalAccessException, NotBlankException {
        field.setAccessible(true);
        Object value = field.get(object);
        if (value == null || ((String) value).trim().isEmpty()) {
            NotBlank annotation = field.getAnnotation(NotBlank.class);
            throw new NotBlankException(annotation.message().replace("{field}", field.getName()));
        }
    }
}
