package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.CheckBox;
import com.github.franckyi.ibeeditor.editor.AbstractProperty;

import java.util.function.Consumer;

public class PropertyBoolean extends AbstractProperty<Boolean> {

    protected CheckBox checkBox;

    public PropertyBoolean(String name, Boolean initialValue, Consumer<Boolean> action) {
        super(initialValue, action);
        checkBox.setText(name);
    }

    @Override
    public Boolean getValue() {
        return checkBox.isChecked();
    }

    @Override
    protected void setValue(Boolean value) {
        checkBox.setChecked(value);
    }

    @Override
    protected void build() {
        this.getNode().setAlignment(Pos.LEFT);
        this.addAll(checkBox = new CheckBox());
        this.getNode().setPadding(Insets.left(5));
    }

    @Override
    protected void updateSize(int listWidth) {
        checkBox.setPrefWidth(listWidth - 59);
    }
}
