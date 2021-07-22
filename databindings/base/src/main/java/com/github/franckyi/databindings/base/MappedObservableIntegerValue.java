package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class MappedObservableIntegerValue<T> extends AbstractMappedObservableValue<T, Integer> implements ObservableIntegerValue {
    public MappedObservableIntegerValue(ObservableValue<T> source, Function<T, Integer> mapper, boolean nullSafe, Integer orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
