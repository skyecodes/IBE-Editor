package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.RenderHelper;
import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaCheckBoxSkinDelegate;

public class VanillaCheckBoxSkin extends AbstractVanillaWidgetSkin<CheckBox, VanillaCheckBoxSkinDelegate> {
    protected VanillaCheckBoxSkin(CheckBox node) {
        super(node, new VanillaCheckBoxSkinDelegate(node));
    }

    @Override
    public int computeHeight(CheckBox node) {
        return 16;
    }

    @Override
    public int computeWidth(CheckBox node) {
        return (node.getLabel() == null || node.getLabel().getString().isEmpty()) ? 16 : RenderHelper.getFontWidth(node.getLabel()) + 20;
    }
}
