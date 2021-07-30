package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.DoubleProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;

public interface Slider extends Control {
    default double getValue() {
        return valueProperty().getValue();
    }

    DoubleProperty valueProperty();

    default void setValue(double value) {
        valueProperty().setValue(value);
    }

    default double getMinValue() {
        return minValueProperty().getValue();
    }

    DoubleProperty minValueProperty();

    default void setMinValue(double value) {
        minValueProperty().setValue(value);
    }

    default double getMaxValue() {
        return maxValueProperty().getValue();
    }

    DoubleProperty maxValueProperty();

    default void setMaxValue(double value) {
        maxValueProperty().setValue(value);
    }

    default double getStep() {
        return stepProperty().getValue();
    }

    DoubleProperty stepProperty();

    default void setStep(double value) {
        stepProperty().setValue(value);
    }

    default LabelFactory getLabelFactory() {
        return labelFactoryProperty().getValue();
    }

    ObjectProperty<LabelFactory> labelFactoryProperty();

    default void setLabelFactory(LabelFactory value) {
        labelFactoryProperty().setValue(value);
    }

    default void increment() {
        if (getValue() < getMaxValue()) {
            valueProperty().incr(getStep());
        }
    }

    default void decrement() {
        if (getValue() > getMinValue()) {
            valueProperty().decr(getStep());
        }
    }

    @FunctionalInterface
    interface LabelFactory {
        Text apply(double value);
    }
}
