package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.Slider;

public interface GenericSliderBuilder<N extends Slider> extends Slider, GenericControlBuilder<N> {
    default N value(double value) {
        return with(n -> n.setValue(value));
    }

    default N labelFactory(LabelFactory value) {
        return with(n -> n.setLabelFactory(value));
    }
}
