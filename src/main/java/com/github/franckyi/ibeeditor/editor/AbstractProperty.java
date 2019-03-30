package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.node.ListExtended;

import java.util.function.Consumer;

public abstract class AbstractProperty<T, V extends Node> extends ListExtended.NodeEntry<V> {

    private final String name;
    private final T initialValue;
    private final Consumer<T> action;

    public AbstractProperty(String name, T initialValue, Consumer<T> action, V node) {
        super(node);
        this.name = name;
        this.initialValue = initialValue;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public T getInitialValue() {
        return initialValue;
    }

    public abstract T getValue();

    public void apply() {
        action.accept(this.getValue());
    }

}
