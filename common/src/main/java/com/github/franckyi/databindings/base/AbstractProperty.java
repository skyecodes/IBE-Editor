package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.*;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractProperty<T> implements Property<T> {
    protected final List<ObservableValueChangeListener<? super T>> listeners = new CopyOnWriteArrayList<>();
    protected T value;
    protected final ObservableValueChangeListener<T> valueListener = (oldVal, newVal) -> doSet(newVal);
    protected ObservableValue<? extends T> boundValue;

    protected AbstractProperty() {
        this(null);
    }

    protected AbstractProperty(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        if (isBound() && !(boundValue instanceof Property<?> property && property.isBound())) {
            throw new IllegalStateException("Property is bound to a value and cannot be set");
        }
        doSet(value);
    }

    private void doSet(T value) {
        if (!Objects.equals(this.value, value)) {
            T old = this.value;
            this.value = value;
            listeners.forEach(listener -> listener.onValueChange(old, value));
        }
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super T> listener) {
        listeners.remove(listener);
    }

    @Override
    public boolean isBound() {
        return boundValue != null;
    }

    @Override
    public void bind(ObservableValue<? extends T> value) {
        unbind();
        set(value.get());
        value.addListener(valueListener);
        boundValue = value;
    }

    @Override
    public void unbind() {
        if (isBound()) {
            boundValue.removeListener(valueListener);
            boundValue = null;
        }
    }

    @Override
    public void bindBidirectional(Property<T> other) {
        bind(other);
        other.bind(this);
    }

    @Override
    public void unbindBidirectional() {
        unbind();
        if (boundValue instanceof Property<?> property) {
            property.unbind();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static class SimpleObjectProperty<T> extends AbstractProperty<T> implements ObjectProperty<T> {
        public SimpleObjectProperty() {
        }

        public SimpleObjectProperty(T value) {
            super(value);
        }
    }

    public static class SimpleStringProperty extends AbstractProperty<String> implements StringProperty {
        public SimpleStringProperty() {
        }

        public SimpleStringProperty(String value) {
            super(value);
        }
    }

    public static class SimpleBooleanProperty extends AbstractProperty<Boolean> implements BooleanProperty {
        public SimpleBooleanProperty() {
            this(false);
        }

        public SimpleBooleanProperty(boolean value) {
            super(value);
        }
    }

    public static class SimpleIntegerProperty extends AbstractProperty<Integer> implements IntegerProperty {
        public SimpleIntegerProperty() {
            this(0);
        }

        public SimpleIntegerProperty(int value) {
            super(value);
        }
    }

    public static class SimpleDoubleProperty extends AbstractProperty<Double> implements DoubleProperty {
        public SimpleDoubleProperty() {
            this(0);
        }

        public SimpleDoubleProperty(double value) {
            super(value);
        }
    }
}
