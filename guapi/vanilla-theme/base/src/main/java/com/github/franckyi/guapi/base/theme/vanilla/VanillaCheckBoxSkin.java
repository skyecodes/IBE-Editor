package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.base.theme.DelegatedSkin;

public class VanillaCheckBoxSkin extends DelegatedSkin<CheckBox> {
    public VanillaCheckBoxSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeHeight(CheckBox node) {
        return 16;
    }

    @Override
    public int computeWidth(CheckBox node) {
        return (node.getLabel() == null || node.getLabel().getRawText().isEmpty()) ? 16 : Game.getClient().getRenderer().getFontWidth(node.getLabel()) + 20;
    }
}
