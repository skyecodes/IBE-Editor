package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.BiFunction;

public class BiMappedObservableIntegerValue<T> extends AbstractBiMappedObservableValue<T, Integer> implements ObservableIntegerValue {
    public BiMappedObservableIntegerValue(ObservableValue<T> a, ObservableValue<T> b, BiFunction<T, T, Integer> mapper) {
        super(a, b, mapper);
    }
}
