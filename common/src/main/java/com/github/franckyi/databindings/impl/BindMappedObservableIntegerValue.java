package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class BindMappedObservableIntegerValue<T> extends AbstractBindMappedObservableValue<T, Integer> implements ObservableIntegerValue {
    public BindMappedObservableIntegerValue(ObservableValue<T> source, Function<T, ObservableValue<Integer>> mapper, boolean nullSafe, Integer orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
