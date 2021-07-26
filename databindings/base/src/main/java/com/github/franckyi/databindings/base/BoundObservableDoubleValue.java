package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableDoubleValue;
import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class BoundObservableDoubleValue<T> extends AbstractBoundObservableValue<T, Double> implements ObservableDoubleValue {
    public BoundObservableDoubleValue(ObservableValue<T> source, Function<T, ObservableValue<Double>> mapper, boolean nullSafe, Double orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
