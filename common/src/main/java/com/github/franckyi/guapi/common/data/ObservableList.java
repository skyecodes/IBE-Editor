package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.ListChangeEvent;

import java.util.Arrays;
import java.util.List;

public interface ObservableList<E> extends List<E> {
    void addListener(ListChangeEvent.Listener<? super E> listener);
    void removeListener(ListChangeEvent.Listener<? super E> listener);

    default boolean addAll(E... elements) {
        return addAll(Arrays.asList(elements));
    }

    default boolean addAll(int index, E... elements) {
        return addAll(index, Arrays.asList(elements));
    }

    default boolean removeAll(E... elements) {
        return removeAll(Arrays.asList(elements));
    }
}
