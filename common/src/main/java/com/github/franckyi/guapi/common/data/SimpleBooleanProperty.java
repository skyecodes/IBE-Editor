package com.github.franckyi.guapi.common.data;

public class SimpleBooleanProperty extends SimpleObjectProperty<Boolean> implements BooleanProperty {
    public SimpleBooleanProperty() {
        this(false);
    }

    public SimpleBooleanProperty(boolean value) {
        super(value);
    }
}
