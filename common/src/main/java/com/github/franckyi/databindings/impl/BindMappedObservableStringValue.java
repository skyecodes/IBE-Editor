package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableStringValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class BindMappedObservableStringValue<T> extends AbstractBindMappedObservableValue<T, String> implements ObservableStringValue {
    public BindMappedObservableStringValue(ObservableValue<T> source, Function<T, ObservableValue<String>> mapper, boolean nullSafe, String orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
