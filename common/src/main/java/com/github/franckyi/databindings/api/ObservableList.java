package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.api.event.ObservableListChangeListener;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
}
