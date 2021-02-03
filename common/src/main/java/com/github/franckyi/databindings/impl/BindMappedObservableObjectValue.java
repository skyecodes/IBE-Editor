package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableObjectValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class BindMappedObservableObjectValue<T, X> extends AbstractBindMappedObservableValue<T, X> implements ObservableObjectValue<X> {
    public BindMappedObservableObjectValue(ObservableValue<T> source, Function<T, ObservableValue<X>> mapper, boolean nullSafe, X orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
