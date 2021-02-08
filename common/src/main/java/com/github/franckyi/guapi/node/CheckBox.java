package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;

public class CheckBox extends Labeled {
    private final BooleanProperty checkedProperty = PropertyFactory.ofBoolean();

    public CheckBox() {
        this("");
    }

    public CheckBox(String text) {
        super(text);
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
