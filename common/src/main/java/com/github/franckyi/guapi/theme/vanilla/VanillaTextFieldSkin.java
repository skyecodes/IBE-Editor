package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.guapi.theme.DelegatedSkin;

public class VanillaTextFieldSkin extends DelegatedSkin<TextField> {
    public VanillaTextFieldSkin(DelegatedRenderer<?> delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeHeight(TextField node) {
        return 20;
    }

    @Override
    public int computeWidth(TextField node) {
        return 200;
    }
}
