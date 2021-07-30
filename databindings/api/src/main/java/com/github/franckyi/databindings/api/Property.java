package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.api.factory.PropertyFactory;

/**
 * A {@link Property} is, to make it simple, a writable {@link ObservableValue}.
 * A {@link Property} can be bound to an {@link ObservableValue}, meaning that its value will always be updated to match
 * the value of the {@link ObservableValue}. A property can also be bound to another {@link Property} in a bidirectional
 * binding, meaning that both properties' value will always be equal.
 * Properties are created through {@link PropertyFactory}.
 *
 * @param <T> The type of the value of this property
 * @see ObservableValue
 */
public interface Property<T> extends ObservableValue<T> {
    /**
     * Sets the value. Notifies the observers if the value has changed.
     *
     * @param value The new value
     */
    void set(T value);

    /**
     * Binds this property to an {@link ObservableValue}, which means that this property's value
     * will always match the {@link ObservableValue}. It can be unbound by using {@link #unbind()}.
     *
     * @param value The {@link ObservableValue} to bind to this property
     */
    void bind(ObservableValue<? extends T> value);

    /**
     * Unbinds this property if it was bound to an ObservableValue using {@link #bind}.
     *
     * @see #bind(ObservableValue)
     */
    void unbind();

    /**
     * @return Whether or not this property is bound to another {@link ObservableValue}
     * or bidirectionnaly bound to another {@link Property}.
     */
    boolean isBound();

    /**
     * Creates a bidirectionnal binding between this property and another property, which means that both
     * properties' value will always be equal. They can be unbound by using {@link #unbindBidirectional}.
     *
     * @param other The other property to bidirectionally bind to this property
     */
    void bindBidirectional(Property<T> other);

    /**
     * Unbinds this property if it was bidirectionally bound to this other property using {@link #bindBidirectional}.
     *
     * @param other The other property to bidirectionally unbind to this property
     * @see #bindBidirectional(Property)
     */
    void unbindBidirectional(Property<T> other);
}
