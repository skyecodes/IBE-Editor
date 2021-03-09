package com.github.franckyi.databindings.api.factory;

import com.github.franckyi.databindings.api.*;

public interface PropertyFactory {
    <T> ObjectProperty<T> ofObject();

    <T> ObjectProperty<T> ofObject(T value);

    StringProperty ofString();

    StringProperty ofString(String value);

    BooleanProperty ofBoolean();

    BooleanProperty ofBoolean(boolean value);

    IntegerProperty ofInteger();

    IntegerProperty ofInteger(int value);

    <T> ObservableObjectValue<T> readOnly(ObjectProperty<T> property);

    ObservableStringValue readOnly(StringProperty property);

    ObservableBooleanValue readOnly(BooleanProperty property);

    ObservableIntegerValue readOnly(IntegerProperty property);
}
