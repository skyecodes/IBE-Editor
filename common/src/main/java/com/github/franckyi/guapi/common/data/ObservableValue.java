package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.PropertyChangeEvent;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ObservableValue<T> {
    T getValue();
    void addListener(PropertyChangeEvent.Listener<? super T> listener);
    void removeListener(PropertyChangeEvent.Listener<? super T> listener);

    default <E> E map(Function<T, E> function) {
        return function.apply(getValue());
    }

    default <E> E mapIfPresent(Function<T, E> function, E defaultValue) {
        if (getValue() == null) return defaultValue;
        return map(function);
    }

    default void doIfPresent(Consumer<T> consumer) {
        if (getValue() == null) return;
        consumer.accept(getValue());
    }
}
