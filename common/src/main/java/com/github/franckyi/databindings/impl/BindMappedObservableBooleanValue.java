package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class BindMappedObservableBooleanValue<T> extends AbstractBindMappedObservableValue<T, Boolean> implements ObservableBooleanValue {
    public BindMappedObservableBooleanValue(ObservableValue<T> source, Function<T, ObservableValue<Boolean>> mapper, boolean nullSafe, Boolean orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
