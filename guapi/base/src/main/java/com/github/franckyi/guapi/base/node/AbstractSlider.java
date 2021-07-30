package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.DoubleProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.Slider;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class AbstractSlider extends AbstractControl implements Slider {
    private final DoubleProperty valueProperty, minValueProperty, maxValueProperty, stepProperty;
    private final ObjectProperty<LabelFactory> labelFactoryProperty = ObjectProperty.create(d -> text(Integer.toString((int) d)));

    protected AbstractSlider() {
        this(0);
    }

    protected AbstractSlider(double value) {
        this(value, 0, 100);
    }

    protected AbstractSlider(double value, double minValue, double maxValue) {
        this(value, minValue, maxValue, 1);
    }

    protected AbstractSlider(double value, double minValue, double maxValue, double step) {
        valueProperty = DoubleProperty.create(value);
        minValueProperty = DoubleProperty.create(minValue);
        maxValueProperty = DoubleProperty.create(maxValue);
        stepProperty = DoubleProperty.create(step);
        minValueProperty.addListener(min -> setValue(Math.max(min, getValue())));
        maxValueProperty.addListener(max -> setValue(Math.min(max, getValue())));
    }

    @Override
    public DoubleProperty valueProperty() {
        return valueProperty;
    }

    @Override
    public DoubleProperty minValueProperty() {
        return minValueProperty;
    }

    @Override
    public DoubleProperty maxValueProperty() {
        return maxValueProperty;
    }

    @Override
    public DoubleProperty stepProperty() {
        return stepProperty;
    }

    @Override
    public ObjectProperty<LabelFactory> labelFactoryProperty() {
        return labelFactoryProperty;
    }
}
