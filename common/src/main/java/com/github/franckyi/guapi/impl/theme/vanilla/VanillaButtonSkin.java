package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.impl.theme.DelegatedSkin;
import com.github.franckyi.minecraft.Minecraft;

public class VanillaButtonSkin extends DelegatedSkin<Button> {
    public VanillaButtonSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(Button node) {
        return Math.max(90, Minecraft.getClient().getRenderer().getFontWidth(node.getLabel()) + 20);
    }

    @Override
    public int computeHeight(Button node) {
        return 20;
    }
}
