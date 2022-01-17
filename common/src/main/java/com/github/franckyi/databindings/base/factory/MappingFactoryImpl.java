package com.github.franckyi.databindings.base.factory;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.api.factory.MappingFactory;
import com.github.franckyi.databindings.base.AbstractBindMappedObservableValue;
import com.github.franckyi.databindings.base.AbstractListMappedObservableValue;
import com.github.franckyi.databindings.base.AbstractSimpleMappedObservableValue;

import java.util.function.*;

public class MappingFactoryImpl implements MappingFactory {
    public static final MappingFactory INSTANCE = new MappingFactoryImpl();

    private MappingFactoryImpl() {
    }

    @Override
    public <T> ObservableObjectValue<T> createMapping(Supplier<T> supplier, ObservableValue<?>... triggers) {
        return new AbstractSimpleMappedObservableValue.SimpleMappedObservableObjectValue<>(supplier, triggers);
    }

    @Override
    public ObservableStringValue createStringMapping(Supplier<String> supplier, ObservableValue<?>... triggers) {
        return new AbstractSimpleMappedObservableValue.SimpleMappedObservableStringValue(supplier, triggers);
    }

    @Override
    public ObservableBooleanValue createBooleanMapping(BooleanSupplier supplier, ObservableValue<?>... triggers) {
        return new AbstractSimpleMappedObservableValue.SimpleMappedObservableBooleanValue(supplier, triggers);
    }

    @Override
    public ObservableIntegerValue createIntegerMapping(IntSupplier supplier, ObservableValue<?>... triggers) {
        return new AbstractSimpleMappedObservableValue.SimpleMappedObservableIntegerValue(supplier, triggers);
    }

    @Override
    public ObservableDoubleValue createDoubleMapping(DoubleSupplier supplier, ObservableValue<?>... triggers) {
        return new AbstractSimpleMappedObservableValue.SimpleMappedObservableDoubleValue(supplier, triggers);
    }

    @Override
    public <T> ObservableObjectValue<T> createPropertyMapping(Supplier<ObservableValue<T>> supplier, ObservableValue<?>... triggers) {
        return new AbstractBindMappedObservableValue.BindMappedObservableObjectValue<>(supplier, triggers);
    }

    @Override
    public ObservableStringValue createStringPropertyMapping(Supplier<ObservableValue<String>> supplier, ObservableValue<?>... triggers) {
        return new AbstractBindMappedObservableValue.BindMappedObservableStringValue(supplier, triggers);
    }

    @Override
    public ObservableBooleanValue createBooleanPropertyMapping(Supplier<ObservableValue<Boolean>> supplier, ObservableValue<?>... triggers) {
        return new AbstractBindMappedObservableValue.BindMappedObservableBooleanValue(supplier, triggers);
    }

    @Override
    public ObservableIntegerValue createIntegerPropertyMapping(Supplier<ObservableValue<Integer>> supplier, ObservableValue<?>... triggers) {
        return new AbstractBindMappedObservableValue.BindMappedObservableIntegerValue(supplier, triggers);
    }

    @Override
    public ObservableDoubleValue createDoublePropertyMapping(Supplier<ObservableValue<Double>> supplier, ObservableValue<?>... triggers) {
        return new AbstractBindMappedObservableValue.BindMappedObservableDoubleValue(supplier, triggers);
    }

    @Override
    public <E, T> ObservableObjectValue<T> createListMapping(ObservableList<E> list, Function<ObservableList<E>, T> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return new AbstractListMappedObservableValue.ListMappedObservableObjectValue<>(list, mapper, triggerFunction);
    }

    @Override
    public <E> ObservableStringValue createListStringMapping(ObservableList<E> list, Function<ObservableList<E>, String> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return new AbstractListMappedObservableValue.ListMappedObservableStringValue<>(list, mapper, triggerFunction);
    }

    @Override
    public <E> ObservableBooleanValue createListBooleanMapping(ObservableList<E> list, Predicate<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return new AbstractListMappedObservableValue.ListMappedObservableBooleanValue<>(list, mapper, triggerFunction);
    }

    @Override
    public <E> ObservableIntegerValue createListIntegerMapping(ObservableList<E> list, ToIntFunction<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return new AbstractListMappedObservableValue.ListMappedObservableIntegerValue<>(list, mapper, triggerFunction);
    }

    @Override
    public <E> ObservableDoubleValue createListDoubleMapping(ObservableList<E> list, ToDoubleFunction<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return new AbstractListMappedObservableValue.ListMappedObservableDoubleValue<>(list, mapper, triggerFunction);
    }
}
