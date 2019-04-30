package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.CheckBox;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;

import java.util.function.Consumer;

public class PropertyBoolean extends AbstractProperty<Boolean> {

    protected CheckBox checkBox;

    public PropertyBoolean(String name, Boolean initialValue, Consumer<Boolean> action) {
        super(initialValue, action);
        checkBox.setText(name);
    }

    @Override
    public Boolean getValue() {
        return checkBox.getValue();
    }

    @Override
    protected void setValue(Boolean value) {
        checkBox.setValue(value);
    }

    @Override
    protected void build() {
        this.getNode().setAlignment(Pos.LEFT);
        this.addAll(checkBox = new CheckBox());
    }

    @Override
    public void updateSize(int listWidth) {
        checkBox.setPrefWidth(listWidth - 60);
    }
}
