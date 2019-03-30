package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.ibeeditor.editor.AbstractProperty;

import java.util.function.Consumer;

public abstract class EmptyProperty<T> extends AbstractProperty<T, HBox> {

    public EmptyProperty(String name, T initialValue, Consumer<T> action) {
        super(name, initialValue, action, new HBox(10));
        this.getNode().setAlignment(Pos.LEFT);
        this.getNode().setPadding(Insets.left(5));
        this.getNode().getChildren().add(new Label(name + " :"));
    }

}
