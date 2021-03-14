package com.github.franckyi.databindings.api.factory;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.*;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Factory for {@link ObservableValue} mappings.
 * @see Bindings#getMappingFactory()
 */
public interface MappingFactory {
    <T, X> ObservableObjectValue<X> map(ObservableValue<T> thisValue, Function<T, X> mapper);

    <T, X> ObservableObjectValue<X> map(ObservableValue<T> thisValue, Function<T, X> mapper, X orIfNull);

    <T> ObservableStringValue mapToString(ObservableValue<T> thisValue, Function<T, String> mapper);

    <T> ObservableStringValue mapToString(ObservableValue<T> thisValue, Function<T, String> mapper, String orIfNull);

    <T> ObservableBooleanValue mapToBoolean(ObservableValue<T> thisValue, Function<T, Boolean> mapper);

    <T> ObservableBooleanValue mapToBoolean(ObservableValue<T> thisValue, Function<T, Boolean> mapper, Boolean orIfNull);

    <T> ObservableIntegerValue mapToInt(ObservableValue<T> thisValue, Function<T, Integer> mapper);

    <T> ObservableIntegerValue mapToInt(ObservableValue<T> thisValue, Function<T, Integer> mapper, Integer orIfNull);

    <T, X> ObservableObjectValue<X> bindMap(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper);

    <T, X> ObservableObjectValue<X> bindMap(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper, X orIfNull);

    <T> ObservableStringValue bindMapToString(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper);

    <T> ObservableStringValue bindMapToString(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper, String orIfNull);

    <T> ObservableBooleanValue bindMapToBoolean(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper);

    <T> ObservableBooleanValue bindMapToBoolean(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper, Boolean orIfNull);

    <T> ObservableIntegerValue bindMapToInt(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper);

    <T> ObservableIntegerValue bindMapToInt(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper, Integer orIfNull);

    <T> ObservableStringValue mapToString(ObservableValue<T> thisValue, ObservableValue<T> otherValue, BiFunction<T, T, String> mapper);

    <T> ObservableBooleanValue mapToBoolean(ObservableValue<T> thisValue, ObservableValue<T> otherValue, BiFunction<T, T, Boolean> mapper);

    <T> ObservableIntegerValue mapToInt(ObservableValue<T> thisValue, ObservableValue<T> otherValue, BiFunction<T, T, Integer> mapper);
}
