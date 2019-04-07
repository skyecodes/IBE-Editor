package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.ibeeditor.editor.AbstractProperty;

import java.util.function.Consumer;

public abstract class EmptyProperty<T> extends AbstractProperty<T, HBox> {

    private final Label nameLabel;

    public EmptyProperty(String name, T initialValue, Consumer<T> action) {
        super(name, initialValue, action, new HBox());
        this.getNode().setAlignment(Pos.LEFT);
        this.getNode().setPadding(new Insets(0, 15, 0, 5));
        nameLabel = new Label(name + " :");
        nameLabel.setPrefWidth(50);
        nameLabel.setMargin(Insets.right(5));
        this.getNode().getChildren().add(nameLabel);
    }

    public Label getNameLabel() {
        return nameLabel;
    }
}
