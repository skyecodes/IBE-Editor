package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableDoubleValue;
import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.BiFunction;

public class BiMappedObservableDoubleValue<T, X> extends AbstractBiMappedObservableValue<T, X, Double> implements ObservableDoubleValue {
    public BiMappedObservableDoubleValue(ObservableValue<T> a, ObservableValue<X> b, BiFunction<T, X, Double> mapper) {
        super(a, b, mapper);
    }
}
