package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaTreeViewSkinDelegate;

public class VanillaTreeViewSkin extends AbstractVanillaListNodeSkin<TreeView<?>, VanillaTreeViewSkinDelegate<?>> {
    protected VanillaTreeViewSkin(TreeView<?> node) {
        super(node, new VanillaTreeViewSkinDelegate<>(node));
    }

    @Override
    public int computeWidth(TreeView<?> node) {
        return 100;
    }

    @Override
    public int computeHeight(TreeView<?> node) {
        return 100;
    }
}
