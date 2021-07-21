package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.Labeled;

public interface GenericLabeledBuilder<N extends Labeled> extends Labeled, GenericControlBuilder<N> {
    default N label(Text value) {
        return with(n -> n.setLabel(value));
    }
}
