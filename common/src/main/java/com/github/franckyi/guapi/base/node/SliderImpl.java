package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.Slider;
import com.github.franckyi.guapi.api.node.builder.SliderBuilder;

public class SliderImpl extends AbstractSlider implements SliderBuilder {
    public SliderImpl() {
    }

    public SliderImpl(double value) {
        super(value);
    }

    public SliderImpl(double value, double minValue, double maxValue) {
        super(value, minValue, maxValue);
    }

    public SliderImpl(double value, double minValue, double maxValue, double step) {
        super(value, minValue, maxValue, step);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<?> getType() {
        return Slider.class;
    }
}
