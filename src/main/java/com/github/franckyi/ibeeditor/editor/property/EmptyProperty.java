package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.ibeeditor.editor.AbstractProperty;

import java.util.function.Consumer;

public abstract class EmptyProperty<T> extends AbstractProperty<T> {

    protected Label nameLabel;

    public EmptyProperty(String name, T initialValue, Consumer<T> action) {
        super(initialValue, action);
        nameLabel.setText(name + " :");
    }

    @Override
    protected void build() {
        this.getNode().setAlignment(Pos.LEFT);
        this.getNode().setPadding(new Insets(0, 5));
        this.addAll(nameLabel = new Label(""));
        nameLabel.setPrefWidth(50);
        nameLabel.setMargin(Insets.right(5));
    }

}
