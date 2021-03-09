package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.event.PropertyChangeListener;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ObservableValue<T> {
    T get();

    void addListener(PropertyChangeListener<? super T> listener);

    default PropertyChangeListener<? super T> addListener(Consumer<? super T> listener) {
        PropertyChangeListener<? super T> realListener = (oldVal, newVal) -> listener.accept(newVal);
        addListener(realListener);
        return realListener;
    }

    default PropertyChangeListener<? super T> addListener(Runnable listener) {
        PropertyChangeListener<? super T> realListener = (oldVal, newVal) -> listener.run();
        addListener(realListener);
        return realListener;
    }

    void removeListener(PropertyChangeListener<? super T> listener);

    static <T> ObservableValue<T> of(T value) {
        return new ObservableValue<T>() {
            @Override
            public T get() {
                return value;
            }

            @Override
            public void addListener(PropertyChangeListener<? super T> listener) {
            }

            @Override
            public void removeListener(PropertyChangeListener<? super T> listener) {
            }
        };
    }

    default <X> ObservableObjectValue<X> map(Function<T, X> mapper) {
        return Bindings.getMappingFactory().map(this, mapper);
    }

    default <X> ObservableObjectValue<X> map(Function<T, X> mapper, X orIfNull) {
        return Bindings.getMappingFactory().map(this, mapper, orIfNull);
    }

    default ObservableStringValue mapToString(Function<T, String> mapper) {
        return Bindings.getMappingFactory().mapToString(this, mapper);
    }

    default ObservableStringValue mapToString(Function<T, String> mapper, String orIfNull) {
        return Bindings.getMappingFactory().mapToString(this, mapper, orIfNull);
    }

    default ObservableBooleanValue mapToBoolean(Function<T, Boolean> mapper) {
        return Bindings.getMappingFactory().mapToBoolean(this, mapper);
    }

    default ObservableBooleanValue mapToBoolean(Function<T, Boolean> mapper, boolean orIfNull) {
        return Bindings.getMappingFactory().mapToBoolean(this, mapper, orIfNull);
    }

    default ObservableIntegerValue mapToInt(Function<T, Integer> mapper) {
        return Bindings.getMappingFactory().mapToInt(this, mapper);
    }

    default ObservableIntegerValue mapToInt(Function<T, Integer> mapper, int orIfNull) {
        return Bindings.getMappingFactory().mapToInt(this, mapper, orIfNull);
    }

    default <X> ObservableObjectValue<X> bindMap(Function<T, ObservableValue<X>> mapper) {
        return Bindings.getMappingFactory().bindMap(this, mapper);
    }

    default <X> ObservableObjectValue<X> bindMap(Function<T, ObservableValue<X>> mapper, X orIfNull) {
        return Bindings.getMappingFactory().bindMap(this, mapper, orIfNull);
    }

    default ObservableStringValue bindMapToString(Function<T, ObservableValue<String>> mapper) {
        return Bindings.getMappingFactory().bindMapToString(this, mapper);
    }

    default ObservableStringValue bindMapToString(Function<T, ObservableValue<String>> mapper, String orIfNull) {
        return Bindings.getMappingFactory().bindMapToString(this, mapper, orIfNull);
    }

    default ObservableBooleanValue bindMapToBoolean(Function<T, ObservableValue<Boolean>> mapper) {
        return Bindings.getMappingFactory().bindMapToBoolean(this, mapper);
    }

    default ObservableBooleanValue bindMapToBoolean(Function<T, ObservableValue<Boolean>> mapper, boolean orIfNull) {
        return Bindings.getMappingFactory().bindMapToBoolean(this, mapper, orIfNull);
    }

    default ObservableIntegerValue bindMapToInt(Function<T, ObservableValue<Integer>> mapper) {
        return Bindings.getMappingFactory().bindMapToInt(this, mapper);
    }

    default ObservableIntegerValue bindMapToInt(Function<T, ObservableValue<Integer>> mapper, int orIfNull) {
        return Bindings.getMappingFactory().bindMapToInt(this, mapper, orIfNull);
    }

    default ObservableBooleanValue mapToBoolean(ObservableValue<T> other, BiFunction<T, T, Boolean> mapper) {
        return Bindings.getMappingFactory().mapToBoolean(this, other, mapper);
    }

    default ObservableIntegerValue mapToInt(ObservableValue<T> other, BiFunction<T, T, Integer> mapper) {
        return Bindings.getMappingFactory().mapToInt(this, other, mapper);
    }

    default ObservableBooleanValue isNull() {
        return mapToBoolean(Objects::isNull);
    }

    default ObservableBooleanValue notNull() {
        return mapToBoolean(Objects::nonNull);
    }

}
