package com.github.franckyi.databindings.api.factory;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.ObservableList;

/**
 * Factory for {@link ObservableList}s.
 * @see Bindings#getObservableListFactory()
 */
public interface ObservableListFactory {
    <E> ObservableList<E> arrayList();
}
