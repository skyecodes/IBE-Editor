package com.github.franckyi.guapi;

import java.util.Set;
import java.util.function.BiConsumer;

public interface ValueNode<T> {

    Set<BiConsumer<T, T>> getOnValueChangedListeners();

    default void onValueChanged(T oldVal, T newVal) {
        this.getOnValueChangedListeners().forEach(listener -> listener.accept(oldVal, newVal));
    }

}
