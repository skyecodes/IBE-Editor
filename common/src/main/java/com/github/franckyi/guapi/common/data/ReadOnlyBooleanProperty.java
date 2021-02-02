package com.github.franckyi.guapi.common.data;

public class ReadOnlyBooleanProperty extends ReadOnlyObjectProperty<Boolean> implements ObservableBooleanValue {
    public ReadOnlyBooleanProperty(ObjectProperty<Boolean> property) {
        super(property);
    }
}
