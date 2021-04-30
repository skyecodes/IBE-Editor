package com.github.franckyi.databindings;

import com.github.franckyi.databindings.api.factory.MappingFactory;
import com.github.franckyi.databindings.api.factory.ObservableListFactory;
import com.github.franckyi.databindings.api.factory.PropertyFactory;
import com.github.franckyi.databindings.impl.factory.MappingFactoryImpl;
import com.github.franckyi.databindings.impl.factory.ObservableListFactoryImpl;
import com.github.franckyi.databindings.impl.factory.PropertyFactoryImpl;

/**
 * The entrypoint for my data binding API. Holds the factories used to create properties, mappings and observable lists.
 *
 * @see com.github.franckyi.databindings.api.ObservableValue
 * @see com.github.franckyi.databindings.api.Property
 * @see com.github.franckyi.databindings.api.ObservableList
 */
public final class DataBindings {
    private static PropertyFactory propertyFactory = PropertyFactoryImpl.INSTANCE;
    private static MappingFactory mappingFactory = MappingFactoryImpl.INSTANCE;
    private static ObservableListFactory observableListFactory = ObservableListFactoryImpl.INSTANCE;

    /**
     * Gets the current property factory.
     * @return The current property factory
     */
    public static PropertyFactory getPropertyFactory() {
        return propertyFactory;
    }

    /**
     * Sets the current property factory.
     * @param propertyFactory The new property factory
     */
    public static void setPropertyFactory(PropertyFactory propertyFactory) {
        DataBindings.propertyFactory = propertyFactory;
    }

    /**
     * Gets the current mapping factory.
     * @return The current mapping factory
     */
    public static MappingFactory getMappingFactory() {
        return mappingFactory;
    }

    /**
     * Sets the current mapping factory.
     * @param mappingFactory The new mapping factory
     */
    public static void setMappingFactory(MappingFactory mappingFactory) {
        DataBindings.mappingFactory = mappingFactory;
    }

    /**
     * Gets the current observable list factory.
     * @return The current observable list factory
     */
    public static ObservableListFactory getObservableListFactory() {
        return observableListFactory;
    }

    /**
     * Sets the current observable list factory.
     * @param observableListFactory The new observable list factory
     */
    public static void setObservableListFactory(ObservableListFactory observableListFactory) {
        DataBindings.observableListFactory = observableListFactory;
    }
}
