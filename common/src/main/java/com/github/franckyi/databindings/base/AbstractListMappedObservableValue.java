package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.api.event.ObservableListChangeEvent;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

public abstract class AbstractListMappedObservableValue<E, T> implements ObservableValue<T>, ObservableValueChangeListener<Object> {
    private final ObjectProperty<T> property = ObjectProperty.create();
    private final ObservableList<E> source;
    private final Function<ObservableList<E>, T> mapper;
    private final Function<E, ObservableValue<?>> triggerFunction;

    public AbstractListMappedObservableValue(ObservableList<E> source, Function<ObservableList<E>, T> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        this.source = source;
        this.mapper = mapper;
        this.triggerFunction = triggerFunction;
        source.addListener(this::updateValue);
        if (triggerFunction != null) {
            source.addListener(this::updateTriggers);
            source.forEach(e -> triggerFunction.apply(e).addListener(this));
        }
        updateValue();
    }

    @Override
    public T get() {
        return property.get();
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super T> listener) {
        property.addListener(listener);
    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super T> listener) {
        property.removeListener(listener);
    }

    @Override
    public void onValueChange(Object oldVal, Object newVal) {
        updateValue();
    }

    private void updateTriggers(ObservableListChangeEvent<? extends E> event) {
        event.getRemoved(true).stream()
                .map(ObservableListChangeEvent.SimpleChangeEntry::getValue)
                .map(triggerFunction)
                .forEach(observableValue -> observableValue.removeListener(this));
        event.getAdded(true).stream()
                .map(ObservableListChangeEvent.SimpleChangeEntry::getValue)
                .map(triggerFunction)
                .forEach(observableValue -> observableValue.addListener(this));
    }

    private void updateValue() {
        property.setValue(mapper.apply(source));
    }

    public static class ListMappedObservableBooleanValue<E> extends AbstractListMappedObservableValue<E, Boolean> implements ObservableBooleanValue {
        public ListMappedObservableBooleanValue(ObservableList<E> source, Predicate<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction) {
            super(source, mapper::test, triggerFunction);
        }
    }

    public static class ListMappedObservableDoubleValue<E> extends AbstractListMappedObservableValue<E, Double> implements ObservableDoubleValue {
        public ListMappedObservableDoubleValue(ObservableList<E> source, ToDoubleFunction<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction) {
            super(source, mapper::applyAsDouble, triggerFunction);
        }
    }

    public static class ListMappedObservableIntegerValue<E> extends AbstractListMappedObservableValue<E, Integer> implements ObservableIntegerValue {
        public ListMappedObservableIntegerValue(ObservableList<E> source, ToIntFunction<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction) {
            super(source, mapper::applyAsInt, triggerFunction);
        }
    }

    public static class ListMappedObservableObjectValue<E, T> extends AbstractListMappedObservableValue<E, T> implements ObservableObjectValue<T> {
        public ListMappedObservableObjectValue(ObservableList<E> source, Function<ObservableList<E>, T> mapper, Function<E, ObservableValue<?>> triggerFunction) {
            super(source, mapper, triggerFunction);
        }
    }

    public static class ListMappedObservableStringValue<E> extends AbstractListMappedObservableValue<E, String> implements ObservableStringValue {
        public ListMappedObservableStringValue(ObservableList<E> source, Function<ObservableList<E>, String> mapper, Function<E, ObservableValue<?>> triggerFunction) {
            super(source, mapper, triggerFunction);
        }
    }
}
