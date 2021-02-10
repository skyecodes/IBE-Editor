package com.github.franckyi.databindings;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.impl.ObservableArrayList;

public final class ObservableListFactory {
    public static <E> ObservableList<E> arrayList() {
        return new ObservableArrayList<>();
    }

}
