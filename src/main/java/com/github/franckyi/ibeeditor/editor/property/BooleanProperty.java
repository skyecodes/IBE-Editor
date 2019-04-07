package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.CheckBox;
import com.github.franckyi.ibeeditor.editor.AbstractProperty;

import java.util.function.Consumer;

public class BooleanProperty extends AbstractProperty<Boolean, HBox> {

    private final CheckBox checkBox;

    public BooleanProperty(String name, Boolean initialValue, Consumer<Boolean> action) {
        super(name, initialValue, action, new HBox());
        this.getNode().setAlignment(Pos.LEFT);
        this.getNode().getChildren().add(checkBox = new CheckBox(name, initialValue));
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    @Override
    public Boolean getValue() {
        return checkBox.isChecked();
    }
}
