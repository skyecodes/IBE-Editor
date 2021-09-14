package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableObjectValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class MappedObservableObjectValue<T, X> extends AbstractMappedObservableValue<T, X> implements ObservableObjectValue<X> {
    public MappedObservableObjectValue(ObservableValue<T> source, Function<T, X> mapper, boolean nullSafe, X orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
