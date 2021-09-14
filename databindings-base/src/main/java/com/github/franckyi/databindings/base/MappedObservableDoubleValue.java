package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableDoubleValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class MappedObservableDoubleValue<T> extends AbstractMappedObservableValue<T, Double> implements ObservableDoubleValue {
    public MappedObservableDoubleValue(ObservableValue<T> source, Function<T, Double> mapper, boolean nullSafe, Double orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
