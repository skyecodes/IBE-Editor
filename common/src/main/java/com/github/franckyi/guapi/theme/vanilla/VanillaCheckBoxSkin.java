package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.node.CheckBox;
import com.github.franckyi.guapi.theme.DelegatedSkin;

public class VanillaCheckBoxSkin extends DelegatedSkin<CheckBox> {
    public VanillaCheckBoxSkin(DelegatedRenderer<?> delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(CheckBox node) {
        return font().getFontWidth(node.getText()) + 24;
    }

    @Override
    public int computeHeight(CheckBox node) {
        return 20;
    }
}
