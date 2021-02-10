package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.VBox;

public interface GenericVBoxBuilder<N extends VBox> extends VBox, GenericGroupBuilder<N>, GenericSpacedBuilder<N>, GenericVerticalParentBuilder<N> {
}
