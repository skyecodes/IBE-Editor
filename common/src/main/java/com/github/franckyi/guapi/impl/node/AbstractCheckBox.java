package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.gamehooks.util.common.Text;
import com.github.franckyi.guapi.api.node.CheckBox;

public abstract class AbstractCheckBox extends AbstractLabeled implements CheckBox {
    private final BooleanProperty checkedProperty = PropertyFactory.ofBoolean();

    protected AbstractCheckBox() {
    }

    protected AbstractCheckBox(String label) {
        super(label);
    }

    protected AbstractCheckBox(Text label) {
        super(label);
    }

    @Override
    public boolean isChecked() {
        return checkedProperty().getValue();
    }

    @Override
    public BooleanProperty checkedProperty() {
        return checkedProperty;
    }

    @Override
    public void setChecked(boolean value) {
        checkedProperty().setValue(value);
    }
}
