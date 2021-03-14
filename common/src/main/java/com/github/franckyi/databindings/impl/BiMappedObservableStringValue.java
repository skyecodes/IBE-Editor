package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableStringValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.BiFunction;

public class BiMappedObservableStringValue<T> extends AbstractBiMappedObservableValue<T, String> implements ObservableStringValue {
    public BiMappedObservableStringValue(ObservableValue<T> a, ObservableValue<T> b, BiFunction<T, T, String> function) {
        super(a, b, function);
    }
}
