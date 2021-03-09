package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.impl.theme.DelegatedSkin;
import com.github.franckyi.minecraft.Minecraft;

public class VanillaCheckBoxSkin extends DelegatedSkin<CheckBox> {
    public VanillaCheckBoxSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(CheckBox node) {
        return Minecraft.getClient().getRenderer().getFontWidth(node.getLabel()) + 24;
    }

    @Override
    public int computeHeight(CheckBox node) {
        return 20;
    }
}
