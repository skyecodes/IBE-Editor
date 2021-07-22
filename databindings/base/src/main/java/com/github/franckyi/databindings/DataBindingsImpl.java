package com.github.franckyi.databindings;

import com.github.franckyi.databindings.base.factory.MappingFactoryImpl;
import com.github.franckyi.databindings.base.factory.ObservableListFactoryImpl;
import com.github.franckyi.databindings.base.factory.PropertyFactoryImpl;

public final class DataBindingsImpl {
    public static void init() {
        DataBindings.setPropertyFactory(PropertyFactoryImpl.INSTANCE);
        DataBindings.setMappingFactory(MappingFactoryImpl.INSTANCE);
        DataBindings.setObservableListFactory(ObservableListFactoryImpl.INSTANCE);
    }
}
