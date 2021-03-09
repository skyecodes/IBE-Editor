package com.github.franckyi.databindings;

import com.github.franckyi.databindings.api.factory.MappingFactory;
import com.github.franckyi.databindings.api.factory.ObservableListFactory;
import com.github.franckyi.databindings.api.factory.PropertyFactory;
import com.github.franckyi.databindings.impl.factory.MappingFactoryImpl;
import com.github.franckyi.databindings.impl.factory.ObservableListFactoryImpl;
import com.github.franckyi.databindings.impl.factory.PropertyFactoryImpl;

public final class Bindings {
    private static PropertyFactory propertyFactory = PropertyFactoryImpl.INSTANCE;
    private static MappingFactory mappingFactory = MappingFactoryImpl.INSTANCE;
    private static ObservableListFactory observableListFactory = ObservableListFactoryImpl.INSTANCE;

    public static PropertyFactory getPropertyFactory() {
        return propertyFactory;
    }

    public static void setPropertyFactory(PropertyFactory propertyFactory) {
        Bindings.propertyFactory = propertyFactory;
    }

    public static MappingFactory getMappingFactory() {
        return mappingFactory;
    }

    public static void setMappingFactory(MappingFactory mappingFactory) {
        Bindings.mappingFactory = mappingFactory;
    }

    public static ObservableListFactory getObservableListFactory() {
        return observableListFactory;
    }

    public static void setObservableListFactory(ObservableListFactory observableListFactory) {
        Bindings.observableListFactory = observableListFactory;
    }
}
