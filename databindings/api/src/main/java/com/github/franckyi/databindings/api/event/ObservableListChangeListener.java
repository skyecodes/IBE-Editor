package com.github.franckyi.databindings.api.event;

import com.github.franckyi.databindings.api.ObservableList;

/**
 * A listener that can be added to an {@link ObservableList} using the
 * {@link ObservableList#addListener} method.
 * @param <E> The type of elements in the list
 */
@FunctionalInterface
public interface ObservableListChangeListener<E> {
    /**
     * Called whenever the list is changed.
     * @param event The list change event
     */
    void onListChange(ObservableListChangeEvent<? extends E> event);
}
