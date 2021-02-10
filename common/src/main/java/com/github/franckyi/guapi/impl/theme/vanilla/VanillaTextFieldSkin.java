package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.impl.theme.DelegatedSkin;

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
        return 150;
    }
}
