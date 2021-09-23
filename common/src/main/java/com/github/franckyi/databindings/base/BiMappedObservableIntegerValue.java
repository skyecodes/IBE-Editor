package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.BiFunction;

public class BiMappedObservableIntegerValue<T, X> extends AbstractBiMappedObservableValue<T, X, Integer> implements ObservableIntegerValue {
    public BiMappedObservableIntegerValue(ObservableValue<T> a, ObservableValue<X> b, BiFunction<T, X, Integer> mapper) {
        super(a, b, mapper);
    }
}
