package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

public abstract class AbstractReadOnlyProperty<T> implements ObservableValue<T> {
    protected final Property<T> property;

    protected AbstractReadOnlyProperty(Property<T> property) {
        this.property = property;
    }

    @Override
    public T get() {
        return property.get();
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super T> listener) {
        property.addListener(listener);
    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super T> listener) {
        property.removeListener(listener);
    }

    @Override
    public String toString() {
        return property.toString();
    }

    public static class ReadOnlyBooleanProperty extends AbstractReadOnlyProperty<Boolean> implements ObservableBooleanValue {
        public ReadOnlyBooleanProperty(Property<Boolean> property) {
            super(property);
        }
    }

    public static class ReadOnlyDoubleProperty extends AbstractReadOnlyProperty<Double> implements ObservableDoubleValue {
        public ReadOnlyDoubleProperty(Property<Double> property) {
            super(property);
        }
    }

    public static class ReadOnlyIntegerProperty extends AbstractReadOnlyProperty<Integer> implements ObservableIntegerValue {
        public ReadOnlyIntegerProperty(Property<Integer> property) {
            super(property);
        }
    }

    public static class ReadOnlyObjectProperty<T> extends AbstractReadOnlyProperty<T> implements ObservableObjectValue<T> {
        public ReadOnlyObjectProperty(Property<T> property) {
            super(property);
        }
    }

    public static class ReadOnlyStringProperty extends AbstractReadOnlyProperty<String> implements ObservableStringValue {
        public ReadOnlyStringProperty(Property<String> property) {
            super(property);
        }
    }
}
