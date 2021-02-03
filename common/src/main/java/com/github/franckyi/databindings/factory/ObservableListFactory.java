package com.github.franckyi.databindings.factory;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.impl.ObservableArrayList;

import java.util.Collection;

public final class ObservableListFactory {
    public static <E> ObservableList<E> arrayList(int initialCapacity) {
        return new ObservableArrayList<>(initialCapacity);
    }

    public static <E> ObservableList<E> arrayList() {
        return new ObservableArrayList<>();
    }

    public static <E> ObservableList<E> arrayList(Collection<? extends E> c) {
        return new ObservableArrayList<>(c);
    }

}
