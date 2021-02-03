package com.github.franckyi.databindings.factory;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.impl.*;

import java.util.function.Function;

public final class MappingFactory {
    public static <T, X> ObservableObjectValue<X> map(ObservableValue<T> thisValue, Function<T, X> mapper) {
        return new MappedObservableObjectValue<>(thisValue, mapper, false, null);
    }

    public static <T, X> ObservableObjectValue<X> map(ObservableValue<T> thisValue, Function<T, X> mapper, X orIfNull) {
        return new MappedObservableObjectValue<>(thisValue, mapper, true, orIfNull);
    }

    public static <T> ObservableStringValue mapToString(ObservableValue<T> thisValue, Function<T, String> mapper) {
        return new MappedObservableStringValue<>(thisValue, mapper, false, null);
    }

    public static <T> ObservableStringValue mapToString(ObservableValue<T> thisValue, Function<T, String> mapper, String orIfNull) {
        return new MappedObservableStringValue<>(thisValue, mapper, true, orIfNull);
    }

    public static <T> ObservableBooleanValue mapToBoolean(ObservableValue<T> thisValue, Function<T, Boolean> mapper) {
        return new MappedObservableBooleanValue<>(thisValue, mapper, false, null);
    }

    public static <T> ObservableBooleanValue mapToBoolean(ObservableValue<T> thisValue, Function<T, Boolean> mapper, Boolean orIfNull) {
        return new MappedObservableBooleanValue<>(thisValue, mapper, true, orIfNull);
    }

    public static <T> ObservableIntegerValue mapToInt(ObservableValue<T> thisValue, Function<T, Integer> mapper) {
        return new MappedObservableIntegerValue<>(thisValue, mapper, false, null);
    }

    public static <T> ObservableIntegerValue mapToInt(ObservableValue<T> thisValue, Function<T, Integer> mapper, Integer orIfNull) {
        return new MappedObservableIntegerValue<>(thisValue, mapper, true, orIfNull);
    }

    public static <T, X> ObservableObjectValue<X> bindMap(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper) {
        return new BindMappedObservableObjectValue<>(thisValue, mapper, false, null);
    }

    public static <T, X> ObservableObjectValue<X> bindMap(ObservableValue<T> thisValue, Function<T, ObservableValue<X>> mapper, X orIfNull) {
        return new BindMappedObservableObjectValue<>(thisValue, mapper, true, orIfNull);
    }

    public static <T> ObservableStringValue bindMapToString(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper) {
        return new BindMappedObservableStringValue<>(thisValue, mapper, false, null);
    }

    public static <T> ObservableStringValue bindMapToString(ObservableValue<T> thisValue, Function<T, ObservableValue<String>> mapper, String orIfNull) {
        return new BindMappedObservableStringValue<>(thisValue, mapper, true, orIfNull);
    }

    public static <T> ObservableBooleanValue bindMapToBoolean(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper) {
        return new BindMappedObservableBooleanValue<>(thisValue, mapper, false, null);
    }

    public static <T> ObservableBooleanValue bindMapToBoolean(ObservableValue<T> thisValue, Function<T, ObservableValue<Boolean>> mapper, Boolean orIfNull) {
        return new BindMappedObservableBooleanValue<>(thisValue, mapper, true, orIfNull);
    }

    public static <T> ObservableIntegerValue bindMapToInt(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper) {
        return new BindMappedObservableIntegerValue<>(thisValue, mapper, false, null);
    }

    public static <T> ObservableIntegerValue bindMapToInt(ObservableValue<T> thisValue, Function<T, ObservableValue<Integer>> mapper, Integer orIfNull) {
        return new BindMappedObservableIntegerValue<>(thisValue, mapper, true, orIfNull);
    }

}
