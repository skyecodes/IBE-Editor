package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class BoundObservableIntegerValue<T> extends AbstractBoundObservableValue<T, Integer> implements ObservableIntegerValue {
    public BoundObservableIntegerValue(ObservableValue<T> source, Function<T, ObservableValue<Integer>> mapper, boolean nullSafe, Integer orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
