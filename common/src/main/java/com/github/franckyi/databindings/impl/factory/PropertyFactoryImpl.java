package com.github.franckyi.databindings.impl.factory;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.api.factory.PropertyFactory;
import com.github.franckyi.databindings.impl.*;

public class PropertyFactoryImpl implements PropertyFactory {
    public static final PropertyFactory INSTANCE = new PropertyFactoryImpl();

    protected PropertyFactoryImpl() {
    }

    @Override
    public <T> ObjectProperty<T> ofObject() {
        return new SimpleObjectProperty<>();
    }

    @Override
    public <T> ObjectProperty<T> ofObject(T value) {
        return new SimpleObjectProperty<>(value);
    }

    @Override
    public StringProperty ofString() {
        return new SimpleStringProperty();
    }

    @Override
    public StringProperty ofString(String value) {
        return new SimpleStringProperty(value);
    }

    @Override
    public BooleanProperty ofBoolean() {
        return new SimpleBooleanProperty();
    }

    @Override
    public BooleanProperty ofBoolean(boolean value) {
        return new SimpleBooleanProperty(value);
    }

    @Override
    public IntegerProperty ofInteger() {
        return new SimpleIntegerProperty();
    }

    @Override
    public IntegerProperty ofInteger(int value) {
        return new SimpleIntegerProperty(value);
    }

    @Override
    public <T> ObservableObjectValue<T> readOnly(ObjectProperty<T> property) {
        return new ReadOnlyObjectProperty<>(property);
    }

    @Override
    public ObservableStringValue readOnly(StringProperty property) {
        return new ReadOnlyStringProperty(property);
    }

    @Override
    public ObservableBooleanValue readOnly(BooleanProperty property) {
        return new ReadOnlyBooleanProperty(property);
    }

    @Override
    public ObservableIntegerValue readOnly(IntegerProperty property) {
        return new ReadOnlyIntegerProperty(property);
    }
}
