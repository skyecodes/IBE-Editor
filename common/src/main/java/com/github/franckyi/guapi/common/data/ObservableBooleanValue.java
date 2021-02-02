package com.github.franckyi.guapi.common.data;

public interface ObservableBooleanValue extends ObservableValue<Boolean> {
    default ObservableBooleanValue mapNot() {
        return mapToBoolean(b -> !b);
    }
}
