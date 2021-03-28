package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.BiFunction;

public class BiMappedObservableBooleanValue<T> extends AbstractBiMappedObservableValue<T, Boolean> implements ObservableBooleanValue {
    public BiMappedObservableBooleanValue(ObservableValue<T> a, ObservableValue<T> b, BiFunction<T, T, Boolean> mapper) {
        super(a, b, mapper);
    }
}
