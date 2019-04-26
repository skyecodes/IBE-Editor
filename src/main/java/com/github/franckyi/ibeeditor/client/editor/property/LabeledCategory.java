package com.github.franckyi.ibeeditor.client.editor.property;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.ibeeditor.client.editor.AbstractProperty;

import java.util.function.Consumer;

public abstract class LabeledCategory<T> extends AbstractProperty<T> {

    protected Label nameLabel;

    public LabeledCategory(String name, T initialValue, Consumer<T> action) {
        this(name, initialValue, action, 50);
    }

    public LabeledCategory(String name, T initialValue, Consumer<T> action, int labelSize) {
        super(initialValue, action);
        nameLabel.setText(name + " :");
        nameLabel.setPrefWidth(labelSize);
    }

    @Override
    protected void build() {
        this.getNode().setAlignment(Pos.LEFT);
        this.getNode().setPadding(new Insets(0, 5));
        this.addAll(nameLabel = new Label(""));
        nameLabel.setMargin(Insets.right(5));
    }

}
