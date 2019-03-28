package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;

import java.util.function.Consumer;

public abstract class AbstractProperty<T> extends ListExtended.NodeEntry<Node> {

    private final String name;
    private final T initialValue;
    private final Consumer<T> action;

    public AbstractProperty(String name, T initialValue, Consumer<T> action) {
        this(name, initialValue, action, new HBox(20));
        HBox node = (HBox) this.getNode();
        Label label = new Label(name);
        node.getChildren().add(label);
        node.setPadding(Insets.left(20));
        node.setAlignment(Pos.LEFT);
    }

    public AbstractProperty(String name, T initialValue, Consumer<T> action, Node node) {
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
