package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;
import com.github.franckyi.databindings.api.factory.MappingFactory;
import com.github.franckyi.databindings.api.factory.PropertyFactory;

import java.util.Objects;
import java.util.function.*;

/**
 * An ObservableValue is a read-only wrapper of an {@link Object} that can be used to track the changes of this object
 * using {@link #addListener(ObservableValueChangeListener)}. A constant, unmodifiable ObservableValue can be created
 * with {@link ObservableValue#unmodifiable}.
 * ObservableValues are usually instances of {@link Property} (created through
 * {@link PropertyFactory}), read-only properties (created through the same
 * factory), or mappings (created through {@link MappingFactory}).
 *
 * @param <T> The type of the value that is observed
 */
public interface ObservableValue<T> extends Supplier<T> {
    /**
     * Gets the value. It is preferable to use the {@code getValue} if available instead.
     *
     * @return The value
     */
    @Override
    T get();

    /**
     * Adds a listener to this {@link ObservableValue} that will be triggered everytime the value is changed.
     *
     * @param listener The listener to add
     */
    void addListener(ObservableValueChangeListener<? super T> listener);

    /**
     * Adds a listener to this {@link ObservableValue} that will be triggered everytime the value is changed.
     * The parameter is wrapped into an {@link ObservableValueChangeListener} which is returned by this method.
     * The parameter will consume the <b>new</b> value (after the change).
     *
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
     * The parameter will simply be run just like any other listener.
     *
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
     *
     * @param listener The listener to remove
     */
    void removeListener(ObservableValueChangeListener<? super T> listener);

    /**
     * Creates an unmodifiable (constant) {@link ObservableValue}.
     *
     * @param value The constant value of the {@link ObservableValue}
     * @param <T>   The type of the value that is observed
     * @return The unmodifiable {@link ObservableValue}
     */
    static <T> ObservableValue<T> unmodifiable(T value) {
        return new Unmodifiable<>() {
            @Override
            public T get() {
                return value;
            }
        };
    }

    abstract class Unmodifiable<T> implements ObservableValue<T> {
        @Override
        public void addListener(ObservableValueChangeListener<? super T> listener) {
        }

        @Override
        public void removeListener(ObservableValueChangeListener<? super T> listener) {
        }
    }

    /**
     * Creates an {@link ObservableValue} that updates whenever other {@link ObservableValue}s are updated (these are called triggers).
     *
     * @param valueSupplier The supplier that is used to update the {@link ObservableValue}
     * @param triggers      The other {@link ObservableValue}s that trigger an update of the {@link ObservableValue}
     * @param <T>           The type of the {@link ObservableValue}
     * @return The {@link ObservableValue}
     */
    static <T> ObservableValue<T> observe(Supplier<T> valueSupplier, ObservableValue<?>... triggers) {
        return DataBindings.getMappingFactory().createMapping(valueSupplier, triggers);
    }

    default <X> ObservableObjectValue<X> map(Function<T, X> mapper) {
        return ObservableObjectValue.observe(() -> mapper.apply(get()), this);
    }

    default <X> ObservableObjectValue<X> map(Function<T, X> mapper, X orIfNull) {
        return ObservableObjectValue.observe(() -> get() == null ? orIfNull : mapper.apply(get()), this);
    }

    default ObservableStringValue mapToString(Function<T, String> mapper) {
        return ObservableStringValue.observe(() -> mapper.apply(get()), this);
    }

    default ObservableStringValue mapToString(Function<T, String> mapper, String orIfNull) {
        return ObservableStringValue.observe(() -> get() == null ? orIfNull : mapper.apply(get()), this);
    }

    default ObservableBooleanValue mapToBoolean(Predicate<T> mapper) {
        return ObservableBooleanValue.observe(() -> mapper.test(get()), this);
    }

    default ObservableBooleanValue mapToBoolean(Predicate<T> mapper, boolean orIfNull) {
        return ObservableBooleanValue.observe(() -> get() == null ? orIfNull : mapper.test(get()), this);
    }

    default ObservableIntegerValue mapToInt(ToIntFunction<T> mapper) {
        return ObservableIntegerValue.observe(() -> mapper.applyAsInt(get()), this);
    }

    default ObservableIntegerValue mapToInt(ToIntFunction<T> mapper, int orIfNull) {
        return ObservableIntegerValue.observe(() -> get() == null ? orIfNull : mapper.applyAsInt(get()), this);
    }

    default ObservableDoubleValue mapToDouble(ToDoubleFunction<T> mapper) {
        return ObservableDoubleValue.observe(() -> mapper.applyAsDouble(get()), this);
    }

    default ObservableDoubleValue mapToDouble(ToDoubleFunction<T> mapper, double orIfNull) {
        return ObservableDoubleValue.observe(() -> get() == null ? orIfNull : mapper.applyAsDouble(get()), this);
    }

    default <X> ObservableObjectValue<X> mapToObservable(Function<T, ObservableValue<X>> mapper, X orIfNull) {
        return mapToObservable(mapper, ObservableValue.unmodifiable(orIfNull));
    }

    default <X> ObservableObjectValue<X> mapToObservable(Function<T, ObservableValue<X>> mapper, ObservableValue<X> orIfNull) {
        return mapToObservable(t -> t == null ? orIfNull : mapper.apply(t));
    }

    default <X> ObservableObjectValue<X> mapToObservable(Function<T, ObservableValue<X>> mapper) {
        return DataBindings.getMappingFactory().createPropertyMapping(() -> mapper.apply(get()), this);
    }

    default ObservableStringValue mapToObservableString(Function<T, ObservableValue<String>> mapper, String orIfNull) {
        return mapToObservableString(mapper, ObservableStringValue.unmodifiable(orIfNull));
    }

    default ObservableStringValue mapToObservableString(Function<T, ObservableValue<String>> mapper, ObservableValue<String> orIfNull) {
        return mapToObservableString(t -> t == null ? orIfNull : mapper.apply(t));
    }

    default ObservableStringValue mapToObservableString(Function<T, ObservableValue<String>> mapper) {
        return DataBindings.getMappingFactory().createStringPropertyMapping(() -> mapper.apply(get()), this);
    }

    default ObservableBooleanValue mapToObservableBoolean(Function<T, ObservableValue<Boolean>> mapper, boolean orIfNull) {
        return mapToObservableBoolean(mapper, orIfNull ? ObservableBooleanValue.TRUE : ObservableBooleanValue.FALSE);
    }

    default ObservableBooleanValue mapToObservableBoolean(Function<T, ObservableValue<Boolean>> mapper, ObservableValue<Boolean> orIfNull) {
        return mapToObservableBoolean(t -> t == null ? orIfNull : mapper.apply(t));
    }

    default ObservableBooleanValue mapToObservableBoolean(Function<T, ObservableValue<Boolean>> mapper) {
        return DataBindings.getMappingFactory().createBooleanPropertyMapping(() -> mapper.apply(get()), this);
    }

    default ObservableIntegerValue mapToObservableInteger(Function<T, ObservableValue<Integer>> mapper, int orIfNull) {
        return mapToObservableInteger(mapper, ObservableIntegerValue.unmodifiable(orIfNull));
    }

    default ObservableIntegerValue mapToObservableInteger(Function<T, ObservableValue<Integer>> mapper, ObservableValue<Integer> orIfNull) {
        return mapToObservableInteger(t -> t == null ? orIfNull : mapper.apply(t));
    }

    default ObservableIntegerValue mapToObservableInteger(Function<T, ObservableValue<Integer>> mapper) {
        return DataBindings.getMappingFactory().createIntegerPropertyMapping(() -> mapper.apply(get()), this);
    }

    default ObservableDoubleValue mapToObservableDouble(Function<T, ObservableValue<Double>> mapper, double orIfNull) {
        return mapToObservableDouble(mapper, ObservableDoubleValue.unmodifiable(orIfNull));
    }

    default ObservableDoubleValue mapToObservableDouble(Function<T, ObservableValue<Double>> mapper, ObservableValue<Double> orIfNull) {
        return mapToObservableDouble(t -> t == null ? orIfNull : mapper.apply(t));
    }

    default ObservableDoubleValue mapToObservableDouble(Function<T, ObservableValue<Double>> mapper) {
        return DataBindings.getMappingFactory().createDoublePropertyMapping(() -> mapper.apply(get()), this);
    }

    /**
     * @return An {@link ObservableBooleanValue} that holds whether this {@link ObservableValue} contains a null value.
     */
    default ObservableBooleanValue isNull() {
        return mapToBoolean(Objects::isNull);
    }

    /**
     * @return An {@link ObservableBooleanValue} that holds whether this {@link ObservableValue} doesn't contain a null value.
     */
    default ObservableBooleanValue notNull() {
        return mapToBoolean(Objects::nonNull);
    }

    /**
     * @return An {@link ObservableBooleanValue} that holds whether this {@link ObservableValue} contains a reference to the value parameter.
     */
    default ObservableBooleanValue is(T value) {
        return mapToBoolean(t -> t == value);
    }

    /**
     * @return An {@link ObservableBooleanValue} that holds whether this {@link ObservableValue} contains a value that is equal to the value parameter.
     */
    default ObservableBooleanValue isEqual(T value) {
        return mapToBoolean(t -> Objects.equals(t, value));
    }

}
