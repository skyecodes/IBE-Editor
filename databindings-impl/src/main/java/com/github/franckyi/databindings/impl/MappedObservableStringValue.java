package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableStringValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class MappedObservableStringValue<T> extends AbstractMappedObservableValue<T, String> implements ObservableStringValue {
    public MappedObservableStringValue(ObservableValue<T> source, Function<T, String> mapper, boolean nullSafe, String orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
