package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.Slider;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.base.theme.DelegatedSkin;

public class VanillaSliderSkin extends DelegatedSkin<Slider> {
    public VanillaSliderSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(Slider node) {
        return 100;
    }

    @Override
    public int computeHeight(Slider node) {
        return 20;
    }
}
