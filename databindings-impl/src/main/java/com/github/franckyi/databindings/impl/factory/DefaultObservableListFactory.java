package com.github.franckyi.databindings.impl.factory;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.factory.ObservableListFactory;
import com.github.franckyi.databindings.impl.ObservableArrayList;

public class DefaultObservableListFactory implements ObservableListFactory {
    public static final ObservableListFactory INSTANCE = new DefaultObservableListFactory();

    protected DefaultObservableListFactory() {
    }

    @Override
    public <E> ObservableList<E> createObservableArrayList() {
        return new ObservableArrayList<>();
    }
}
