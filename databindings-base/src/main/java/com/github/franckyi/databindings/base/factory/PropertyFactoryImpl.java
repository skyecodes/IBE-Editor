package com.github.franckyi.databindings.base.factory;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.api.factory.PropertyFactory;
import com.github.franckyi.databindings.base.*;

public class PropertyFactoryImpl implements PropertyFactory {
    public static final PropertyFactory INSTANCE = new PropertyFactoryImpl();

    protected PropertyFactoryImpl() {
    }

    @Override
    public <T> ObjectProperty<T> createObjectProperty() {
        return new SimpleObjectProperty<>();
    }

    @Override
    public <T> ObjectProperty<T> createObjectProperty(T value) {
        return new SimpleObjectProperty<>(value);
    }

    @Override
    public StringProperty createStringProperty() {
        return new SimpleStringProperty();
    }

    @Override
    public StringProperty createStringProperty(String value) {
        return new SimpleStringProperty(value);
    }

    @Override
    public BooleanProperty createBooleanProperty() {
        return new SimpleBooleanProperty();
    }

    @Override
    public BooleanProperty createBooleanProperty(boolean value) {
        return new SimpleBooleanProperty(value);
    }

    @Override
    public IntegerProperty createIntegerProperty() {
        return new SimpleIntegerProperty();
    }

    @Override
    public IntegerProperty createIntegerProperty(int value) {
        return new SimpleIntegerProperty(value);
    }

    @Override
    public <T> ObservableObjectValue<T> createReadOnlyProperty(ObjectProperty<T> property) {
        return new ReadOnlyObjectProperty<>(property);
    }

    @Override
    public ObservableStringValue createReadOnlyProperty(StringProperty property) {
        return new ReadOnlyStringProperty(property);
    }

    @Override
    public ObservableBooleanValue createReadOnlyProperty(BooleanProperty property) {
        return new ReadOnlyBooleanProperty(property);
    }

    @Override
    public ObservableIntegerValue createReadOnlyProperty(IntegerProperty property) {
        return new ReadOnlyIntegerProperty(property);
    }
}
