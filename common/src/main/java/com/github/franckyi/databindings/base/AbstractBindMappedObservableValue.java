package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.*;

import java.util.function.Supplier;

public abstract class AbstractBindMappedObservableValue<T> extends AbstractMappedObservableValue<T, ObservableValue<T>> {
    public AbstractBindMappedObservableValue(Supplier<? extends ObservableValue<T>> valueSupplier, ObservableValue<?>... triggers) {
        super(ObjectProperty::bind, valueSupplier, triggers);
    }

    public static class BindMappedObservableBooleanValue extends AbstractBindMappedObservableValue<Boolean> implements ObservableBooleanValue {
        public BindMappedObservableBooleanValue(Supplier<ObservableValue<Boolean>> supplier, ObservableValue<?>... triggers) {
            super(supplier, triggers);
        }
    }

    public static class BindMappedObservableDoubleValue extends AbstractBindMappedObservableValue<Double> implements ObservableDoubleValue {
        public BindMappedObservableDoubleValue(Supplier<ObservableValue<Double>> supplier, ObservableValue<?>... triggers) {
            super(supplier, triggers);
        }
    }

    public static class BindMappedObservableIntegerValue extends AbstractBindMappedObservableValue<Integer> implements ObservableIntegerValue {
        public BindMappedObservableIntegerValue(Supplier<ObservableValue<Integer>> supplier, ObservableValue<?>... triggers) {
            super(supplier, triggers);
        }
    }

    public static class BindMappedObservableObjectValue<T> extends AbstractBindMappedObservableValue<T> implements ObservableObjectValue<T> {
        public BindMappedObservableObjectValue(Supplier<ObservableValue<T>> supplier, ObservableValue<?>... triggers) {
            super(supplier, triggers);
        }
    }

    public static class BindMappedObservableStringValue extends AbstractBindMappedObservableValue<String> implements ObservableStringValue {
        public BindMappedObservableStringValue(Supplier<ObservableValue<String>> supplier, ObservableValue<?>... triggers) {
            super(supplier, triggers);
        }
    }
}
