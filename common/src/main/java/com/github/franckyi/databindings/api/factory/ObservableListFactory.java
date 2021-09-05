package com.github.franckyi.databindings.api.factory;

import com.github.franckyi.databindings.api.DataBindings;
import com.github.franckyi.databindings.api.ObservableList;

import java.util.Collection;

/**
 * Factory for {@link ObservableList}s.
 *
 * @see DataBindings#getObservableListFactory()
 */
public interface ObservableListFactory {
    <E> ObservableList<E> createObservableArrayList();

    <E> ObservableList<E> createObservableArrayList(Collection<? extends E> c);
}
