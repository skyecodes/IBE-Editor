package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * An ObservableValue is a read-only wrapper of an {@link Object} that can be used to track the changes of this object
 * using {@link #addListener(ObservableValueChangeListener)}. A constant, unmodifiable ObservableValue can be created
 * with {@link ObservableValue#unmodifiable}.
 * ObservableValues are usually instances of {@link Property} (created through
 * {@link com.github.franckyi.databindings.api.factory.PropertyFactory}), read-only properties (created through the same
 * factory), or mappings (created through {@link com.github.franckyi.databindings.api.factory.MappingFactory}).
 * @param <T> The type of the value that is observed
 */
public interface ObservableValue<T> {
    /**
     * Gets the value. It is preferable to use the {@code getValue} if available instead.
     * @return The value
     */
    T get();

    /**
     * Adds a listener to this {@link ObservableValue} that will be triggered everytime the value is changed.
     * @param listener The listener to add
     */
    void addListener(ObservableValueChangeListener<? super T> listener);

    /**
     * Adds a listener to this {@link ObservableValue} that will be triggered everytime the value is changed.
     * The parameter is wrapped into an {@link ObservableValueChangeListener} which is returned by this method.
     * The parameter will consume the <b>new</b> value (after the change).
     * @param listener The listener to ass
     * @return The actual listener that was added
     * @see ObservableValue#addListener(ObservableValueChangeListener)
     */
    default ObservableValueChangeListener<? super T> addListener(Consumer<? super T> listener) {
        ObservableValueChangeListener<? super T> realListener = (oldVal, newVal) -> listener.accept(newVal);
        addListener(realListener);
        return realListener;
    }

    /**
     * Adds a listener to this {@link ObservableValue} that will be triggered everytime the value is changed.
     * The parameter is wrapped into an {@link ObservableValueChangeListener} which is returned by this method.
     * The parameter will simply be ran just like any other listener.
     * @param listener The listener to add
     * @return The actual listener that was added
     * @see ObservableValue#addListener(ObservableValueChangeListener)
     */
    default ObservableValueChangeListener<? super T> addListener(Runnable listener) {
        ObservableValueChangeListener<? super T> realListener = (oldVal, newVal) -> listener.run();
        addListener(realListener);
        return realListener;
    }

    /**
     * Removes a listener from this {@link ObservableValue}.
     * @param listener The listener to remove
     */
    void removeListener(ObservableValueChangeListener<? super T> listener);

    /**
     * Creates an unmodifiable (constant) {@link ObservableValue}.
     * @param value The constant value of the {@link ObservableValue}
     * @param <T> The type of the value that is observed
     * @return The unmodifiable {@link ObservableValue}
     */
    static <T> ObservableValue<T> unmodifiable(T value) {
        return new ObservableValue<T>() {
            @Override
            public T get() {
                return value;
            }

            @Override
            public void addListener(ObservableValueChangeListener<? super T> listener) {
            }

            @Override
            public void removeListener(ObservableValueChangeListener<? super T> listener) {
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

    default ObservableStringValue mapToString(ObservableValue<T> other, BiFunction<T, T, String> mapper) {
        return Bindings.getMappingFactory().mapToString(this, other, mapper);
    }

    default ObservableBooleanValue mapToBoolean(ObservableValue<T> other, BiFunction<T, T, Boolean> mapper) {
        return Bindings.getMappingFactory().mapToBoolean(this, other, mapper);
    }

    default ObservableIntegerValue mapToInt(ObservableValue<T> other, BiFunction<T, T, Integer> mapper) {
        return Bindings.getMappingFactory().mapToInt(this, other, mapper);
    }

    /**
     * @return An {@link ObservableBooleanValue} that holds whether or not this {@link ObservableValue} is null.
     */
    default ObservableBooleanValue isNull() {
        return mapToBoolean(Objects::isNull);
    }

    /**
     * @return An {@link ObservableBooleanValue} that holds whether or not this {@link ObservableValue} is not null.
     */
    default ObservableBooleanValue notNull() {
        return mapToBoolean(Objects::nonNull);
    }

}
