package com.github.franckyi.guapi.api.node.builder;

import java.util.function.Consumer;

public interface Builder<T> {
    @SuppressWarnings("unchecked")
    default T getThis() {
        return (T) this;
    }

    default T with(Consumer<T> with) {
        with.accept(getThis());
        return getThis();
    }
}
