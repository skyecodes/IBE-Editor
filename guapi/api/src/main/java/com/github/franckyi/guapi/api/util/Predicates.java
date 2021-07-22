package com.github.franckyi.guapi.api.util;

import java.util.function.Consumer;
import java.util.function.Predicate;

public final class Predicates {
    public static final Predicate<String> IS_BYTE = tryPredicate(Byte::parseByte);
    public static final Predicate<String> IS_SHORT = tryPredicate(Short::parseShort);
    public static final Predicate<String> IS_INT = tryPredicate(Integer::parseInt);
    public static final Predicate<String> IS_LONG = tryPredicate(Long::parseLong);
    public static final Predicate<String> IS_FLOAT = tryPredicate(Float::parseFloat);
    public static final Predicate<String> IS_DOUBLE = tryPredicate(Double::parseDouble);

    public static Predicate<String> tryPredicate(Consumer<String> action) {
        return s -> {
            try {
                action.accept(s);
                return true;
            } catch (Exception e) {
                return false;
            }
        };
    }
}
