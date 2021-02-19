package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.impl.theme.DelegatedSkin;

@SuppressWarnings("rawtypes")
public class VanillaTreeViewSkin extends DelegatedSkin<TreeView> {
    public VanillaTreeViewSkin(DelegatedRenderer<?> delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(TreeView node) {
        return 100;
    }

    @Override
    public int computeHeight(TreeView node) {
        return 100;
    }
}
