package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.Slider;
import com.github.franckyi.guapi.base.theme.vanilla.delegate.VanillaSliderSkinDelegate;

public class VanillaSliderSkin extends AbstractVanillaWidgetSkin<Slider, VanillaSliderSkinDelegate> {
    public VanillaSliderSkin(Slider node) {
        super(node, new VanillaSliderSkinDelegate(node));
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
