package com.github.franckyi.guapi.theme.vanilla.base;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;

public class VanillaCheckBoxSkin extends AbstractVanillaButtonSkin<CheckBox> {
    public VanillaCheckBoxSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(CheckBox node) {
        return Game.getClient().getRenderer().getFontWidth(node.getLabel()) + 24;
    }
}
