package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.VBox;

public interface GenericVBoxBuilder<N extends VBox> extends VBox, GenericBoxBuilder<N>, GenericVerticalParentBuilder<N> {
}
