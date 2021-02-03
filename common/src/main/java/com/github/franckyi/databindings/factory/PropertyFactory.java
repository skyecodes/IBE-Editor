package com.github.franckyi.databindings.factory;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.impl.*;

public final class PropertyFactory {
    public static <T> ObjectProperty<T> ofObject() {
        return new SimpleObjectProperty<>();
    }

    public static <T> ObjectProperty<T> ofObject(T value) {
        return new SimpleObjectProperty<>(value);
    }

    public static StringProperty ofString() {
        return new SimpleStringProperty();
    }

    public static StringProperty ofString(String value) {
        return new SimpleStringProperty(value);
    }

    public static BooleanProperty ofBoolean() {
        return ofBoolean(false);
    }

    public static BooleanProperty ofBoolean(boolean value) {
        return new SimpleBooleanProperty(value);
    }

    public static IntegerProperty ofInteger() {
        return ofInteger(0);
    }

    public static IntegerProperty ofInteger(int value) {
        return new SimpleIntegerProperty(value);
    }

    public static <T> ObservableObjectValue<T> readOnly(ObjectProperty<T> property) {
        return new ReadOnlyObjectProperty<>(property);
    }

    public static ObservableStringValue readOnly(StringProperty property) {
        return new ReadOnlyStringProperty(property);
    }

    public static ObservableBooleanValue readOnly(BooleanProperty property) {
        return new ReadOnlyBooleanProperty(property);
    }

    public static ObservableIntegerValue readOnly(IntegerProperty property) {
        return new ReadOnlyIntegerProperty(property);
    }

}
