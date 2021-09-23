package com.github.franckyi.databindings.api;

/**
 * A {@link Property} that holds a boolean value.
 */
public interface BooleanProperty extends Property<Boolean>, ObservableBooleanValue {
    static BooleanProperty create() {
        return DataBindings.getPropertyFactory().createBooleanProperty();
    }

    static BooleanProperty create(boolean value) {
        return DataBindings.getPropertyFactory().createBooleanProperty(value);
    }

    /**
     * Sets the value of the property (safer than {@link #set} since it does not allow {@code null} values).
     *
     * @param value The new value
     */
    default void setValue(boolean value) {
        set(value);
    }

    default void toggle() {
        setValue(!getValue());
    }
}
