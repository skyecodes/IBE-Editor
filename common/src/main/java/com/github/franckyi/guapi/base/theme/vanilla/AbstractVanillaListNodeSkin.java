package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.ListNode;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.AbstractVanillaListNodeSkinDelegate;

public abstract class AbstractVanillaListNodeSkin<N extends ListNode<?>, W extends AbstractVanillaListNodeSkinDelegate<?, ?, ?>> extends AbstractVanillaWidgetSkin<N, W> {
    protected AbstractVanillaListNodeSkin(N node, W widget) {
        super(node, widget);
    }
}
