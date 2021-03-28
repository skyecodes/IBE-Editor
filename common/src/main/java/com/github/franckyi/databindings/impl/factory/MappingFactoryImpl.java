package com.github.franckyi.databindings.impl.factory;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.api.factory.MappingFactory;
import com.github.franckyi.databindings.impl.*;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MappingFactoryImpl implements MappingFactory {
    public static final MappingFactory INSTANCE = new MappingFactoryImpl();

    protected MappingFactoryImpl() {
    }

    @Override
    public <T, X> ObservableObjectValue<X> map(ObservableValue<T> thisValue, Function<T, X> mapper) {
        return new MappedObservableObjectValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T, X> ObservableObjectValue<X> map(ObservableValue<T> thisValue, Function<T, X> mapper, X orIfNull) {
        return new MappedObservableObjectValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableStringValue mapToString(ObservableValue<T> thisValue, Function<T, String> mapper) {
        return new MappedObservableStringValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableStringValue mapToString(ObservableValue<T> thisValue, Function<T, String> mapper, String orIfNull) {
        return new MappedObservableStringValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableBooleanValue mapToBoolean(ObservableValue<T> thisValue, Function<T, Boolean> mapper) {
        return new MappedObservableBooleanValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableBooleanValue mapToBoolean(ObservableValue<T> thisValue, Function<T, Boolean> mapper, Boolean orIfNull) {
        return new MappedObservableBooleanValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableIntegerValue mapToInt(ObservableValue<T> thisValue, Function<T, Integer> mapper) {
        return new MappedObservableIntegerValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableIntegerValue mapToInt(ObservableValue<T> thisValue, Function<T, Integer> mapper, Integer orIfNull) {
        return new MappedObservableIntegerValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T, X> ObservableObjectValue<X> bindMap(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper) {
        return new BoundObservableObjectValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T, X> ObservableObjectValue<X> bindMap(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper, X orIfNull) {
        return new BoundObservableObjectValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableStringValue bindMapToString(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper) {
        return new BoundObservableStringValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableStringValue bindMapToString(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper, String orIfNull) {
        return new BoundObservableStringValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableBooleanValue bindMapToBoolean(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper) {
        return new BoundObservableBooleanValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableBooleanValue bindMapToBoolean(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper, Boolean orIfNull) {
        return new BoundObservableBooleanValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableIntegerValue bindMapToInt(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper) {
        return new BoundObservableIntegerValue<>(thisValue, mapper, false, null);
    }

    @Override
    public <T> ObservableIntegerValue bindMapToInt(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper, Integer orIfNull) {
        return new BoundObservableIntegerValue<>(thisValue, mapper, true, orIfNull);
    }

    @Override
    public <T> ObservableStringValue mapToString(ObservableValue<T> thisValue, ObservableValue<T> otherValue, BiFunction<T, T, String> mapper) {
        return new BiMappedObservableStringValue<>(thisValue, otherValue, mapper);
    }

    @Override
    public <T> ObservableBooleanValue mapToBoolean(ObservableValue<T> thisValue, ObservableValue<T> otherValue, BiFunction<T, T, Boolean> mapper) {
        return new BiMappedObservableBooleanValue<>(thisValue, otherValue, mapper);
    }

    @Override
    public <T> ObservableIntegerValue mapToInt(ObservableValue<T> thisValue, ObservableValue<T> otherValue, BiFunction<T, T, Integer> mapper) {
        return new BiMappedObservableIntegerValue<>(thisValue, otherValue, mapper);
    }

    @Override
    public <T> ObservableBooleanValue reduceToBoolean(ObservableList<T> values, Predicate<Stream<T>> reducer) {
        return new ReducedObservableBooleanValue<>(values, reducer);
    }
}
