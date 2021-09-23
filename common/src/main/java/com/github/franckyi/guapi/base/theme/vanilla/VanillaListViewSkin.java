package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaListViewSkinDelegate;

public class VanillaListViewSkin extends AbstractVanillaListNodeSkin<ListView<?>, VanillaListViewSkinDelegate<?>> {
    public VanillaListViewSkin(ListView<?> node) {
        super(node, new VanillaListViewSkinDelegate<>(node));
    }

    @Override
    public int computeWidth(ListView<?> node) {
        return 100;
    }

    @Override
    public int computeHeight(ListView<?> node) {
        return node.getItemHeight() * node.getItems().size();
    }

}
