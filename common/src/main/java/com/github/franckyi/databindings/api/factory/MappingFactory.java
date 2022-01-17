package com.github.franckyi.databindings.api.factory;

import com.github.franckyi.databindings.api.*;

import java.util.function.*;

/**
 * Factory for {@link ObservableValue} mappings.
 *
 * @see DataBindings#getMappingFactory()
 */
public interface MappingFactory {
    <T> ObservableObjectValue<T> createMapping(Supplier<T> supplier, ObservableValue<?>... triggers);

    ObservableStringValue createStringMapping(Supplier<String> supplier, ObservableValue<?>... triggers);

    ObservableBooleanValue createBooleanMapping(BooleanSupplier supplier, ObservableValue<?>... triggers);

    ObservableIntegerValue createIntegerMapping(IntSupplier supplier, ObservableValue<?>... triggers);

    ObservableDoubleValue createDoubleMapping(DoubleSupplier supplier, ObservableValue<?>... triggers);

    <T> ObservableObjectValue<T> createPropertyMapping(Supplier<ObservableValue<T>> supplier, ObservableValue<?>... triggers);

    ObservableStringValue createStringPropertyMapping(Supplier<ObservableValue<String>> supplier, ObservableValue<?>... triggers);

    ObservableBooleanValue createBooleanPropertyMapping(Supplier<ObservableValue<Boolean>> supplier, ObservableValue<?>... triggers);

    ObservableIntegerValue createIntegerPropertyMapping(Supplier<ObservableValue<Integer>> supplier, ObservableValue<?>... triggers);

    ObservableDoubleValue createDoublePropertyMapping(Supplier<ObservableValue<Double>> supplier, ObservableValue<?>... triggers);

    <E, T> ObservableObjectValue<T> createListMapping(ObservableList<E> list, Function<ObservableList<E>, T> mapper, Function<E, ObservableValue<?>> triggerFunction);

    <E> ObservableStringValue createListStringMapping(ObservableList<E> list, Function<ObservableList<E>, String> mapper, Function<E, ObservableValue<?>> triggerFunction);

    <E> ObservableBooleanValue createListBooleanMapping(ObservableList<E> list, Predicate<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction);

    <E> ObservableIntegerValue createListIntegerMapping(ObservableList<E> list, ToIntFunction<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction);

    <E> ObservableDoubleValue createListDoubleMapping(ObservableList<E> list, ToDoubleFunction<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction);
}
