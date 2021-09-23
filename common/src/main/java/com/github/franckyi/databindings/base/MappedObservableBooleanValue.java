package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class MappedObservableBooleanValue<T> extends AbstractMappedObservableValue<T, Boolean> implements ObservableBooleanValue {
    public MappedObservableBooleanValue(ObservableValue<T> source, Function<T, Boolean> mapper, boolean nullSafe, Boolean orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
