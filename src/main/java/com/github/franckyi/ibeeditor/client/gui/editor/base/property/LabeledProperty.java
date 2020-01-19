package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;

import java.util.function.Consumer;

public abstract class LabeledProperty<T> extends AbstractProperty<T> {

    protected Label nameLabel;

    public LabeledProperty(String name, T initialValue, Consumer<T> action) {
        this(name, initialValue, action, 50);
    }

    public LabeledProperty(String name, T initialValue, Consumer<T> action, int labelSize) {
        super(initialValue, action);
        nameLabel.setText(name);
        nameLabel.setPrefWidth(labelSize);
    }

    @Override
    protected void build() {
        this.getNode().setAlignment(Pos.LEFT);
        this.addAll(nameLabel = new Label(""));
        nameLabel.setMargin(Insets.right(5));
    }

}
