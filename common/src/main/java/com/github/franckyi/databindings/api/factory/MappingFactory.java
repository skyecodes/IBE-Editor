package com.github.franckyi.databindings.api.factory;

import com.github.franckyi.databindings.api.*;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Factory for {@link ObservableValue} mappings.
 *
 * @see DataBindings#getMappingFactory()
 */
public interface MappingFactory {
    <T, X> ObservableObjectValue<X> createMapping(ObservableValue<T> thisValue, Function<T, X> mapper);

    <T, X> ObservableObjectValue<X> createMapping(ObservableValue<T> thisValue, Function<T, X> mapper, X orIfNull);

    <T> ObservableStringValue createStringMapping(ObservableValue<T> thisValue, Function<T, String> mapper);

    <T> ObservableStringValue createStringMapping(ObservableValue<T> thisValue, Function<T, String> mapper, String orIfNull);

    <T> ObservableBooleanValue createBooleanMapping(ObservableValue<T> thisValue, Function<T, Boolean> mapper);

    <T> ObservableBooleanValue createBooleanMapping(ObservableValue<T> thisValue, Function<T, Boolean> mapper, Boolean orIfNull);

    <T> ObservableIntegerValue createIntMapping(ObservableValue<T> thisValue, Function<T, Integer> mapper);

    <T> ObservableIntegerValue createIntMapping(ObservableValue<T> thisValue, Function<T, Integer> mapper, Integer orIfNull);

    <T> ObservableDoubleValue createDoubleMapping(ObservableValue<T> thisValue, Function<T, Double> mapper);

    <T> ObservableDoubleValue createDoubleMapping(ObservableValue<T> thisValue, Function<T, Double> mapper, Double orIfNull);

    <T, X> ObservableObjectValue<X> createBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper);

    <T, X> ObservableObjectValue<X> createBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper, X orIfNull);

    <T> ObservableStringValue createStringBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper);

    <T> ObservableStringValue createStringBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper, String orIfNull);

    <T> ObservableBooleanValue createBooleanBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper);

    <T> ObservableBooleanValue createBooleanBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper, Boolean orIfNull);

    <T> ObservableIntegerValue createIntBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper);

    <T> ObservableIntegerValue createIntBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper, Integer orIfNull);

    <T> ObservableDoubleValue createDoubleBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Double>> mapper);

    <T> ObservableDoubleValue createDoubleBoundMapping(ObservableValue<T> thisValue, Function<T, ObservableValue<Double>> mapper, Double orIfNull);

    <T, X> ObservableStringValue createStringBiMapping(ObservableValue<T> thisValue, ObservableValue<X> otherValue, BiFunction<T, X, String> mapper);

    <T, X> ObservableBooleanValue createBooleanBiMapping(ObservableValue<T> thisValue, ObservableValue<X> otherValue, BiFunction<T, X, Boolean> mapper);

    <T, X> ObservableIntegerValue createIntBiMapping(ObservableValue<T> thisValue, ObservableValue<X> otherValue, BiFunction<T, X, Integer> mapper);

    <T, X> ObservableDoubleValue createDoubleBiMapping(ObservableValue<T> thisValue, ObservableValue<X> otherValue, BiFunction<T, X, Double> mapper);
}
