package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.event.ObservableListChangeListener;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface ObservableList<E> extends List<E> {
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

    default ObservableBooleanValue reduceToBoolean(Predicate<Stream<E>> reducer) {
        return Bindings.getMappingFactory().reduceToBoolean(this, reducer);
    }
}
