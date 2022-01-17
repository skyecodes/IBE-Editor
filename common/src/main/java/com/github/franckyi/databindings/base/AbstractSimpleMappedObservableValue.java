package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.*;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public abstract class AbstractSimpleMappedObservableValue<T> extends AbstractMappedObservableValue<T, T> {
    public AbstractSimpleMappedObservableValue(Supplier<T> valueSupplier, ObservableValue<?>... triggers) {
        super(ObjectProperty::set, valueSupplier, triggers);
    }

    public static class SimpleMappedObservableBooleanValue extends AbstractSimpleMappedObservableValue<Boolean> implements ObservableBooleanValue {
        public SimpleMappedObservableBooleanValue(BooleanSupplier supplier, ObservableValue<?>... triggers) {
            super(supplier::getAsBoolean, triggers);
        }
    }

    public static class SimpleMappedObservableDoubleValue extends AbstractSimpleMappedObservableValue<Double> implements ObservableDoubleValue {
        public SimpleMappedObservableDoubleValue(DoubleSupplier supplier, ObservableValue<?>... triggers) {
            super(supplier::getAsDouble, triggers);
        }
    }

    public static class SimpleMappedObservableIntegerValue extends AbstractSimpleMappedObservableValue<Integer> implements ObservableIntegerValue {
        public SimpleMappedObservableIntegerValue(IntSupplier supplier, ObservableValue<?>... triggers) {
            super(supplier::getAsInt, triggers);
        }
    }

    public static class SimpleMappedObservableObjectValue<X> extends AbstractSimpleMappedObservableValue<X> implements ObservableObjectValue<X> {
        public SimpleMappedObservableObjectValue(Supplier<X> supplier, ObservableValue<?>... triggers) {
            super(supplier, triggers);
        }
    }

    public static class SimpleMappedObservableStringValue extends AbstractSimpleMappedObservableValue<String> implements ObservableStringValue {
        public SimpleMappedObservableStringValue(Supplier<String> supplier, ObservableValue<?>... triggers) {
            super(supplier, triggers);
        }
    }
}
