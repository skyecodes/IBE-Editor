package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.BiFunction;

public class BiMappedObservableBooleanValue<T, X> extends AbstractBiMappedObservableValue<T, X, Boolean> implements ObservableBooleanValue {
    public BiMappedObservableBooleanValue(ObservableValue<T> a, ObservableValue<X> b, BiFunction<T, X, Boolean> mapper) {
        super(a, b, mapper);
    }
}
