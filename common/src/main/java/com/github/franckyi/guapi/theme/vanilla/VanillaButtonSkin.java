package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.theme.DelegatedSkin;

public class VanillaButtonSkin extends DelegatedSkin<Button> {
    public VanillaButtonSkin(DelegatedRenderer<?> delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(Button node) {
        return Math.max(90, font().getFontWidth(node.getText()) + 20);
    }

    @Override
    public int computeHeight(Button node) {
        return 20;
    }
}
