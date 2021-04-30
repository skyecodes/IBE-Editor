package com.github.franckyi.databindings.impl.factory;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.api.factory.MappingFactory;
import com.github.franckyi.databindings.impl.*;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MappingFactoryImpl implements MappingFactory {
    public static final MappingFactory INSTANCE = new MappingFactoryImpl();

    protected MappingFactoryImpl() {
    }

    @Override
    public <T, X> ObservableObjectValue<X> createMapping(ObservableValue<T> thisValue, Function<T, X> mapper) {
        return new MappedObservableObjectValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T, X> ObservableObjectValue<X> createMapping(ObservableValue<T> thisValue, Function<T, X> mapper, X orIfNull) {
        return new MappedObservableObjectValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableStringValue createStringMapping(ObservableValue<T> thisValue, Function<T, String> mapper) {
        return new MappedObservableStringValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableStringValue createStringMapping(ObservableValue<T> thisValue, Function<T, String> mapper, String orIfNull) {
        return new MappedObservableStringValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableBooleanValue createBooleanMapping(ObservableValue<T> thisValue, Function<T, Boolean> mapper) {
        return new MappedObservableBooleanValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableBooleanValue createBooleanMapping(ObservableValue<T> thisValue, Function<T, Boolean> mapper, Boolean orIfNull) {
        return new MappedObservableBooleanValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableIntegerValue createIntMapping(ObservableValue<T> thisValue, Function<T, Integer> mapper) {
        return new MappedObservableIntegerValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableIntegerValue createIntMapping(ObservableValue<T> thisValue, Function<T, Integer> mapper, Integer orIfNull) {
        return new MappedObservableIntegerValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T, X> ObservableObjectValue<X> createBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper) {
        return new BoundObservableObjectValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T, X> ObservableObjectValue<X> createBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper, X orIfNull) {
        return new BoundObservableObjectValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableStringValue createStringBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper) {
        return new BoundObservableStringValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableStringValue createStringBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper, String orIfNull) {
        return new BoundObservableStringValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableBooleanValue createBooleanBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper) {
        return new BoundObservableBooleanValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableBooleanValue createBooleanBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper, Boolean orIfNull) {
        return new BoundObservableBooleanValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableIntegerValue createIntBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper) {
        return new BoundObservableIntegerValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableIntegerValue createIntBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper, Integer orIfNull) {
        return new BoundObservableIntegerValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T, X> ObservableStringValue createStringBiMapping(ObservableValue<T> thisValue, ObservableValue<X> otherValue, BiFunction<T, X, String> mapper) {
        return new BiMappedObservableStringValue<>(thisValue, otherValue, mapper);
    }

    @Override
    public <T, X> ObservableBooleanValue createBooleanBiMapping(ObservableValue<T> thisValue, ObservableValue<X> otherValue, BiFunction<T, X, Boolean> mapper) {
        return new BiMappedObservableBooleanValue<>(thisValue, otherValue, mapper);
    }

    @Override
    public <T, X> ObservableIntegerValue createIntBiMapping(ObservableValue<T> thisValue, ObservableValue<X> otherValue, BiFunction<T, X, Integer> mapper) {
        return new BiMappedObservableIntegerValue<>(thisValue, otherValue, mapper);
    }
}
