package com.github.franckyi.guapi.common.data;

import java.util.function.Function;

class MappedObservableIntegerValue<T> extends MappedObservableValue<T, Integer> implements ObservableIntegerValue {
    public MappedObservableIntegerValue(ObservableValue<T> source, Function<T, Integer> mapper) {
        super(source, mapper, 0);
    }
}
