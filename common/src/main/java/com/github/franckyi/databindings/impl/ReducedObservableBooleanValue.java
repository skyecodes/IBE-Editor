package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableList;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class ReducedObservableBooleanValue<T> extends AbstractReducedObservableValue<T, Boolean> implements ObservableBooleanValue {
    public ReducedObservableBooleanValue(ObservableList<T> values, Predicate<Stream<T>> reducer) {
        super(values, reducer::test);
    }
}
