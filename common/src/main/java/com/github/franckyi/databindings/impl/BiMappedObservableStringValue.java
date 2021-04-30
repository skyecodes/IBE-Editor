package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableStringValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.BiFunction;

public class BiMappedObservableStringValue<T, X> extends AbstractBiMappedObservableValue<T, X, String> implements ObservableStringValue {
    public BiMappedObservableStringValue(ObservableValue<T> a, ObservableValue<X> b, BiFunction<T, X, String> mapper) {
        super(a, b, mapper);
    }
}
