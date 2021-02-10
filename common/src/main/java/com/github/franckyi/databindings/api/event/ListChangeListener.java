package com.github.franckyi.databindings.api.event;

@FunctionalInterface
public interface ListChangeListener<E> {
    void onChange(ListChangeEvent<? extends E> event);
}
