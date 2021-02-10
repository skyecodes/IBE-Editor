package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.HBox;

public interface GenericHBoxBuilder<N extends HBox> extends HBox, GenericGroupBuilder<N>, GenericSpacedBuilder<N>, GenericHorizontalParentBuilder<N> {
}
