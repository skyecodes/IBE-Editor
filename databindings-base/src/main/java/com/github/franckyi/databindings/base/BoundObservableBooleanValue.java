package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.Function;

public class BoundObservableBooleanValue<T> extends AbstractBoundObservableValue<T, Boolean> implements ObservableBooleanValue {
    public BoundObservableBooleanValue(ObservableValue<T> source, Function<T, ObservableValue<Boolean>> mapper, boolean nullSafe, Boolean orIfNull) {
        super(source, mapper, nullSafe, orIfNull);
    }
}
