package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableObjectValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class BoundObservableObjectValue<T, X> extends AbstractBoundObservableValue<T, X> implements ObservableObjectValue<X> {
    public BoundObservableObjectValue(ObservableValue<T> source, Function<T, ObservableValue<X>> mapper, boolean nullSafe, X orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
