package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.impl.theme.DelegatedSkin;

public class VanillaTexturedButtonSkin extends DelegatedSkin<TexturedButton> {
    public VanillaTexturedButtonSkin(DelegatedRenderer<?> delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(TexturedButton node) {
        return 20;
    }

    @Override
    public int computeHeight(TexturedButton node) {
        return 20;
    }
}
