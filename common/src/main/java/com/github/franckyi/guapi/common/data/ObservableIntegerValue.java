package com.github.franckyi.guapi.common.data;

public interface ObservableIntegerValue extends ObservableValue<Integer> {
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
}
