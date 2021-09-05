package com.github.franckyi.databindings.base.factory;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.factory.ObservableListFactory;
import com.github.franckyi.databindings.base.ObservableArrayList;

import java.util.Collection;

public class ObservableListFactoryImpl implements ObservableListFactory {
    public static final ObservableListFactory INSTANCE = new ObservableListFactoryImpl();

    protected ObservableListFactoryImpl() {
    }

    @Override
    public <E> ObservableList<E> createObservableArrayList() {
        return new ObservableArrayList<>();
    }

    @Override
    public <E> ObservableList<E> createObservableArrayList(Collection<? extends E> c) {
        return new ObservableArrayList<>(c);
    }
}
