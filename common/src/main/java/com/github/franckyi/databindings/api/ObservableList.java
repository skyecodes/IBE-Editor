package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.api.event.ObservableListChangeListener;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

public interface ObservableList<E> extends List<E> {
    static <E> ObservableList<E> create() {
        return DataBindings.getObservableListFactory().createObservableArrayList();
    }

    static <E> ObservableList<E> create(Collection<? extends E> c) {
        return DataBindings.getObservableListFactory().createObservableArrayList(c);
    }

    void addListener(ObservableListChangeListener<? super E> listener);

    void removeListener(ObservableListChangeListener<? super E> listener);

    default ObservableListChangeListener<? super E> addListener(Runnable listener) {
        ObservableListChangeListener<? super E> realListener = event -> listener.run();
        addListener(realListener);
        return realListener;
    }

    @SuppressWarnings("unchecked")
    default boolean addAll(E... elements) {
        return addAll(Arrays.asList(elements));
    }

    @SuppressWarnings("unchecked")
    default boolean addAll(int index, E... elements) {
        return addAll(index, Arrays.asList(elements));
    }

    @SuppressWarnings("unchecked")
    default boolean removeAll(E... elements) {
        return removeAll(Arrays.asList(elements));
    }

    @SuppressWarnings("unchecked")
    default void setAll(E... elements) {
        setAll(Arrays.asList(elements));
    }

    default void setAll(Collection<? extends E> c) {
        clear();
        addAll(c);
    }

    default ObservableBooleanValue allMatch(Predicate<E> predicate, Function<E, ObservableValue<?>> triggerFunction) {
        return mapToBoolean(list -> list.stream().allMatch(predicate), triggerFunction);
    }

    default ObservableBooleanValue anyMatch(Predicate<E> predicate, Function<E, ObservableValue<?>> triggerFunction) {
        return mapToBoolean(list -> list.stream().anyMatch(predicate), triggerFunction);
    }

    default ObservableBooleanValue noneMatch(Predicate<E> predicate, Function<E, ObservableValue<?>> triggerFunction) {
        return mapToBoolean(list -> list.stream().noneMatch(predicate), triggerFunction);
    }

    default ObservableIntegerValue sizeProperty() {
        return mapToInt(List::size, null);
    }

    default <X> ObservableValue<X> map(Function<ObservableList<E>, X> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return DataBindings.getMappingFactory().createListMapping(this, mapper, triggerFunction);
    }

    default ObservableStringValue mapToString(Function<ObservableList<E>, String> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return DataBindings.getMappingFactory().createListStringMapping(this, mapper, triggerFunction);
    }

    default ObservableBooleanValue mapToBoolean(Predicate<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return DataBindings.getMappingFactory().createListBooleanMapping(this, mapper, triggerFunction);
    }

    default ObservableIntegerValue mapToInt(ToIntFunction<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return DataBindings.getMappingFactory().createListIntegerMapping(this, mapper, triggerFunction);
    }

    default ObservableDoubleValue mapToDouble(ToDoubleFunction<ObservableList<E>> mapper, Function<E, ObservableValue<?>> triggerFunction) {
        return DataBindings.getMappingFactory().createListDoubleMapping(this, mapper, triggerFunction);
    }
}
