package com.github.franckyi.databindings;

import com.github.franckyi.databindings.impl.factory.DefaultMappingFactory;
import com.github.franckyi.databindings.impl.factory.DefaultObservableListFactory;
import com.github.franckyi.databindings.impl.factory.DefaultPropertyFactory;

public final class DefaultDataBindings {
    public static void init() {
        DataBindings.setPropertyFactory(DefaultPropertyFactory.INSTANCE);
        DataBindings.setMappingFactory(DefaultMappingFactory.INSTANCE);
        DataBindings.setObservableListFactory(DefaultObservableListFactory.INSTANCE);
    }
}
