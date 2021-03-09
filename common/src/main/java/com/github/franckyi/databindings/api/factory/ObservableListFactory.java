package com.github.franckyi.databindings.api.factory;

import com.github.franckyi.databindings.api.ObservableList;

public interface ObservableListFactory {
    <E> ObservableList<E> arrayList();
}
