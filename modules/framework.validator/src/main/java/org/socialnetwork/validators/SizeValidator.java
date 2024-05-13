package org.socialnetwork.validators;

import org.socialnetwork.annotations.Size;

import java.lang.reflect.Field;

public class SizeValidator implements FieldValidator {
    @Override
    public boolean supports(Field field) {
        return field.isAnnotationPresent(Size.class);
    }

    @Override
    public void validate(Field field, Object object) throws IllegalAccessException, IllegalArgumentException {
        field.setAccessible(true);
        Object value = field.get(object);
        Size size = field.getAnnotation(Size.class);
        if (value instanceof String stringValue) {
            int length = stringValue.length();
            if (length < size.min() || length > size.max()) {
                throw new IllegalArgumentException(
                        size.message().replace("{min}", String.valueOf(size.min()))
                                .replace("{max}", String.valueOf(size.max()))
                );
            }
        }
    }
}
