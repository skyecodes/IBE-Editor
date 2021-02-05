package com.github.franckyi.databindings.api;

public interface ObservableBooleanValue extends ObservableValue<Boolean> {
    default boolean getValue() {
        return get() != null && get();
    }

    default ObservableBooleanValue not() {
        return mapToBoolean(b -> !b);
    }

    default ObservableBooleanValue or(ObservableValue<Boolean> value) {
        return mapToBoolean(value, (x, y) -> x || y);
    }

    default ObservableBooleanValue and(ObservableValue<Boolean> value) {
        return mapToBoolean(value, (x, y) -> x && y);
    }
}
