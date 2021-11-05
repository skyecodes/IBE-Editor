package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.ItemView;
import net.minecraft.world.item.ItemStack;

public interface GenericItemViewBuilder<N extends ItemView> extends ItemView, GenericControlBuilder<N> {
    default N item(ItemStack value) {
        return with(n -> n.setItem(value));
    }

    default N renderDecorations(boolean value) {
        return with(n -> n.setRenderDecorations(value));
    }

    default N renderDecorations() {
        return renderDecorations(true);
    }
}
