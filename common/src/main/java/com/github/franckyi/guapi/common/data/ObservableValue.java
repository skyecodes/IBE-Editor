package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.PropertyChangeEvent;

import java.util.Objects;
import java.util.function.Function;

public interface ObservableValue<T> {
    T getValue();

    void addListener(PropertyChangeEvent.Listener<? super T> listener);

    void removeListener(PropertyChangeEvent.Listener<? super T> listener);

    static <T> ObservableValue<T> of(T value) {
        return new ObservableValue<T>() {
            @Override
            public T getValue() {
                return value;
            }

            @Override
            public void addListener(PropertyChangeEvent.Listener<? super T> listener) {
            }

            @Override
            public void removeListener(PropertyChangeEvent.Listener<? super T> listener) {
            }
        };
    }

    default <X> ObservableValue<X> bindMap(Function<T, ObservableValue<X>> mapper) {
        return new BindMappedObservableValue<>(this, mapper);
    }

    default <X> ObservableValue<X> safeBindMap(Function<T, ObservableValue<X>> mapper, X nullValue) {
        return new BindMappedObservableValue<>(this, mapper, nullValue);
    }

    default <X> ObservableValue<X> map(Function<T, X> mapper) {
        return new MappedObservableValue<>(this, mapper);
    }

    default <X> ObservableValue<X> safeMap(Function<T, X> mapper, X nullValue) {
        return new MappedObservableValue<>(this, mapper, nullValue);
    }

    default ObservableBooleanValue mapToBoolean(Function<T, Boolean> mapper) {
        return new MappedObservableBooleanValue<>(this, mapper);
    }

    default ObservableIntegerValue mapToInt(Function<T, Integer> mapper) {
        return new MappedObservableIntegerValue<>(this, mapper);
    }

    default ObservableBooleanValue mapIsNull() {
        return mapToBoolean(Objects::isNull);
    }

    default ObservableBooleanValue mapNotNull() {
        return mapToBoolean(Objects::nonNull);
    }

}
