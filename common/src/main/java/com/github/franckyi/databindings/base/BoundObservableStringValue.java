package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableStringValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class BoundObservableStringValue<T> extends AbstractBoundObservableValue<T, String> implements ObservableStringValue {
    public BoundObservableStringValue(ObservableValue<T> source, Function<T, ObservableValue<String>> mapper, boolean nullSafe, String orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
