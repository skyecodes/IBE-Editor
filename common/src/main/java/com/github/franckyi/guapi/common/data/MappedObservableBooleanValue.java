package com.github.franckyi.guapi.common.data;

import java.util.function.Function;

class MappedObservableBooleanValue<T> extends MappedObservableValue<T, Boolean> implements ObservableBooleanValue {
    public MappedObservableBooleanValue(ObservableValue<T> source, Function<T, Boolean> mapper) {
        super(source, mapper, false);
    }
}
