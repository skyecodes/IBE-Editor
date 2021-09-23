package com.github.franckyi.databindings.api.factory;

import com.github.franckyi.databindings.api.*;

/**
 * Factory for {@link Property}s.
 *
 * @see DataBindings#getPropertyFactory()
 */
public interface PropertyFactory {
    <T> ObjectProperty<T> createObjectProperty();

    <T> ObjectProperty<T> createObjectProperty(T value);

    StringProperty createStringProperty();

    StringProperty createStringProperty(String value);

    BooleanProperty createBooleanProperty();

    BooleanProperty createBooleanProperty(boolean value);

    IntegerProperty createIntegerProperty();

    IntegerProperty createIntegerProperty(int value);

    DoubleProperty createDoubleProperty();

    DoubleProperty createDoubleProperty(double value);

    <T> ObservableObjectValue<T> createReadOnlyProperty(ObjectProperty<T> property);

    ObservableStringValue createReadOnlyProperty(StringProperty property);

    ObservableBooleanValue createReadOnlyProperty(BooleanProperty property);

    ObservableIntegerValue createReadOnlyProperty(IntegerProperty property);

    ObservableDoubleValue createReadOnlyProperty(DoubleProperty property);
}
