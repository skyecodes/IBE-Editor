package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.Labeled;
import net.minecraft.network.chat.Component;

public interface GenericLabeledBuilder<N extends Labeled> extends Labeled, GenericControlBuilder<N> {
    default N label(Component value) {
        return with(n -> n.setLabel(value));
    }
}
