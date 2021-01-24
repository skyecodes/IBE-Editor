package com.github.franckyi.guapi.common.data;

public class ReadOnlyProperty<T> implements ObservableValue<T> {
    private final Property<T> property;

    public ReadOnlyProperty(Property<T> property) {
        this.property = property;
    }

    @Override
    public T getValue() {
        return property.getValue();
    }

    @Override
    public void addListener(ChangeListener<? super T> listener) {
        property.addListener(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super T> listener) {
        property.removeListener(listener);
    }
}
