package com.github.franckyi.guapi.common.data;

public class ReadOnlyIntegerProperty extends ReadOnlyObjectProperty<Integer> implements ObservableIntegerValue {
    public ReadOnlyIntegerProperty(ObjectProperty<Integer> property) {
        super(property);
    }
}
