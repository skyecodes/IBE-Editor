package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.event.ListChangeEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface ObservableList<E> extends List<E> {
    void addListener(ListChangeEvent.Listener<? super E> listener);
    void removeListener(ListChangeEvent.Listener<? super E> listener);

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
        int i = 0;
        for (E e : c) {
            if (i < size()) {
                set(i++, e);
            } else {
                add(e);
            }
        }
        for (; i < size(); i++) {
            remove(i);
        }
    }
}
