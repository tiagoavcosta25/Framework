package org.socialnetwork.definitions;

public record Result<T>(T data, String[] context) implements IValue {

    public static <T> Result<T> of(T data, String... contexts) {
        return new Result<T>(data, contexts);
    }
}
