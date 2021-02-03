package com.github.franckyi.databindings.api;

public interface BooleanProperty extends Property<Boolean>, ObservableBooleanValue {
    default void setValue(boolean value) {
        set(value);
    }
}
