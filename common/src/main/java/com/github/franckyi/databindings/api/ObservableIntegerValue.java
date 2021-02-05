package com.github.franckyi.databindings.api;

public interface ObservableIntegerValue extends ObservableValue<Integer> {
    default int getValue() {
        return get() == null ? 0 : get();
    }

    default ObservableIntegerValue add(int value) {
        return mapToInt(i -> i + value);
    }

    default ObservableIntegerValue substract(int value) {
        return mapToInt(i -> i - value);
    }

    default ObservableIntegerValue multiply(int value) {
        return mapToInt(i -> i * value);
    }

    default ObservableIntegerValue divide(int value) {
        return mapToInt(i -> i / value);
    }

    default ObservableIntegerValue add(ObservableValue<Integer> value) {
        return mapToInt(value, Integer::sum);
    }

    default ObservableIntegerValue substract(ObservableValue<Integer> value) {
        return mapToInt(value, (x, y) -> x - y);
    }

    default ObservableIntegerValue multiply(ObservableValue<Integer> value) {
        return mapToInt(value, (x, y) -> x * y);
    }

    default ObservableIntegerValue divide(ObservableValue<Integer> value) {
        return mapToInt(value, (x, y) -> x / y);
    }
}
