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
    public static final Predicate<String> HEX_COLOR = s -> {
        try {
            if (s.startsWith("#")) {
                s = s.substring(1);
            }
            if (s.length() != 6) {
                return false;
            }
            Integer.parseInt(s, 16);
            return true;
        } catch (Exception e) {
            return false;
        }
    };

    public static Predicate<String> range(int min, int max) {
        return s -> {
            try {
                int i = Integer.parseInt(s);
                return i >= min && i < max;
            } catch (Exception e) {
                return false;
            }
        };
    }

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
