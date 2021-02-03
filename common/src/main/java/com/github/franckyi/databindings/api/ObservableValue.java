package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.event.PropertyChangeEvent;
import com.github.franckyi.databindings.factory.MappingFactory;

import java.util.Objects;
import java.util.function.Function;

public interface ObservableValue<T> {
    T get();

    void addListener(PropertyChangeEvent.Listener<? super T> listener);

    void removeListener(PropertyChangeEvent.Listener<? super T> listener);

    static <T> ObservableValue<T> of(T value) {
        return new ObservableValue<T>() {
            @Override
            public T get() {
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

    default <X> ObservableObjectValue<X> map(Function<T, X> mapper) {
        return MappingFactory.map(this, mapper);
    }

    default <X> ObservableObjectValue<X> map(Function<T, X> mapper, X orIfNull) {
        return MappingFactory.map(this, mapper, orIfNull);
    }

    default ObservableStringValue mapToString(Function<T, String> mapper) {
        return MappingFactory.mapToString(this, mapper);
    }

    default ObservableStringValue mapToString(Function<T, String> mapper, String orIfNull) {
        return MappingFactory.mapToString(this, mapper, orIfNull);
    }

    default ObservableBooleanValue mapToBoolean(Function<T, Boolean> mapper) {
        return MappingFactory.mapToBoolean(this, mapper);
    }

    default ObservableBooleanValue mapToBoolean(Function<T, Boolean> mapper, boolean orIfNull) {
        return MappingFactory.mapToBoolean(this, mapper, orIfNull);
    }

    default ObservableIntegerValue mapToInt(Function<T, Integer> mapper) {
        return MappingFactory.mapToInt(this, mapper);
    }

    default ObservableIntegerValue mapToInt(Function<T, Integer> mapper, int orIfNull) {
        return MappingFactory.mapToInt(this, mapper, orIfNull);
    }

    default <X> ObservableObjectValue<X> bindMap(Function<T, ObservableValue<X>> mapper) {
        return MappingFactory.bindMap(this, mapper);
    }

    default <X> ObservableObjectValue<X> bindMap(Function<T, ObservableValue<X>> mapper, X orIfNull) {
        return MappingFactory.bindMap(this, mapper, orIfNull);
    }

    default ObservableStringValue bindMapToString(Function<T, ObservableValue<String>> mapper) {
        return MappingFactory.bindMapToString(this, mapper);
    }

    default ObservableStringValue bindMapToString(Function<T, ObservableValue<String>> mapper, String orIfNull) {
        return MappingFactory.bindMapToString(this, mapper, orIfNull);
    }

    default ObservableBooleanValue bindMapToBoolean(Function<T, ObservableValue<Boolean>> mapper) {
        return MappingFactory.bindMapToBoolean(this, mapper);
    }

    default ObservableBooleanValue bindMapToBoolean(Function<T, ObservableValue<Boolean>> mapper, boolean orIfNull) {
        return MappingFactory.bindMapToBoolean(this, mapper, orIfNull);
    }

    default ObservableIntegerValue bindMapToInt(Function<T, ObservableValue<Integer>> mapper) {
        return MappingFactory.bindMapToInt(this, mapper);
    }

    default ObservableIntegerValue bindMapToInt(Function<T, ObservableValue<Integer>> mapper, int orIfNull) {
        return MappingFactory.bindMapToInt(this, mapper, orIfNull);
    }

    default ObservableBooleanValue isNull() {
        return mapToBoolean(Objects::isNull);
    }

    default ObservableBooleanValue notNull() {
        return mapToBoolean(Objects::nonNull);
    }

}
