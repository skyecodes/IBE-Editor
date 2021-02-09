package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.gamehooks.api.common.text.Text;

public class CheckBox extends Labeled {
    private final BooleanProperty checkedProperty = PropertyFactory.ofBoolean();

    public CheckBox() {
        this(Text.EMPTY);
    }

    public CheckBox(String label) {
        this(Text.literal(label));
    }

    public CheckBox(Text label) {
        super(label);
    }

    public boolean isChecked() {
        return checkedProperty().getValue();
    }

    public BooleanProperty checkedProperty() {
        return checkedProperty;
    }

    public void setChecked(boolean value) {
        checkedProperty().setValue(value);
    }
}
