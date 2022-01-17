package com.github.franckyi.databindings.base.factory;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.api.factory.PropertyFactory;
import com.github.franckyi.databindings.base.AbstractProperty;
import com.github.franckyi.databindings.base.AbstractReadOnlyProperty;

public class PropertyFactoryImpl implements PropertyFactory {
    public static final PropertyFactory INSTANCE = new PropertyFactoryImpl();

    protected PropertyFactoryImpl() {
    }

    @Override
    public <T> ObjectProperty<T> createObjectProperty() {
        return new AbstractProperty.SimpleObjectProperty<>();
    }

    @Override
    public <T> ObjectProperty<T> createObjectProperty(T value) {
        return new AbstractProperty.SimpleObjectProperty<>(value);
    }

    @Override
    public StringProperty createStringProperty() {
        return new AbstractProperty.SimpleStringProperty();
    }

    @Override
    public StringProperty createStringProperty(String value) {
        return new AbstractProperty.SimpleStringProperty(value);
    }

    @Override
    public BooleanProperty createBooleanProperty() {
        return new AbstractProperty.SimpleBooleanProperty();
    }

    @Override
    public BooleanProperty createBooleanProperty(boolean value) {
        return new AbstractProperty.SimpleBooleanProperty(value);
    }

    @Override
    public IntegerProperty createIntegerProperty() {
        return new AbstractProperty.SimpleIntegerProperty();
    }

    @Override
    public IntegerProperty createIntegerProperty(int value) {
        return new AbstractProperty.SimpleIntegerProperty(value);
    }

    @Override
    public DoubleProperty createDoubleProperty() {
        return new AbstractProperty.SimpleDoubleProperty();
    }

    @Override
    public DoubleProperty createDoubleProperty(double value) {
        return new AbstractProperty.SimpleDoubleProperty(value);
    }

    @Override
    public <T> ObservableObjectValue<T> createReadOnlyProperty(ObjectProperty<T> property) {
        return new AbstractReadOnlyProperty.ReadOnlyObjectProperty<>(property);
    }

    @Override
    public ObservableStringValue createReadOnlyProperty(StringProperty property) {
        return new AbstractReadOnlyProperty.ReadOnlyStringProperty(property);
    }

    @Override
    public ObservableBooleanValue createReadOnlyProperty(BooleanProperty property) {
        return new AbstractReadOnlyProperty.ReadOnlyBooleanProperty(property);
    }

    @Override
    public ObservableIntegerValue createReadOnlyProperty(IntegerProperty property) {
        return new AbstractReadOnlyProperty.ReadOnlyIntegerProperty(property);
    }

    @Override
    public ObservableDoubleValue createReadOnlyProperty(DoubleProperty property) {
        return new AbstractReadOnlyProperty.ReadOnlyDoubleProperty(property);
    }
}
