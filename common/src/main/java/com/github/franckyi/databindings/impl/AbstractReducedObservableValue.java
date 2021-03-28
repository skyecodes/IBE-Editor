package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class AbstractReducedObservableValue<T, X> implements ObservableValue<X>, ObservableValueChangeListener<X> {
    private final ObservableList<T> values;
    private final Function<Stream<T>, Boolean> mapper;
    private final ObjectProperty<X> value = Bindings.getPropertyFactory().ofObject();

    public AbstractReducedObservableValue(ObservableList<T> values, Function<Stream<T>, Boolean> mapper) {
        this.values = values;
        this.mapper = mapper;
    }

    @Override
    public X get() {
        return null;
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super X> listener) {

    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super X> listener) {

    }

    @Override
    public void onValueChange(X oldVal, X newVal) {

    }
}
