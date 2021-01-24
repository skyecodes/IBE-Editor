package com.github.franckyi.guapi.common.theme.vanilla;

import com.github.franckyi.guapi.common.node.Label;
import com.github.franckyi.guapi.common.skin.Skin;
import com.github.franckyi.guapi.common.skin.Theme;

public class VanillaTheme implements Theme {
    public static final Theme INSTANCE = new VanillaTheme();

    @Override
    public Skin<Label> getLabelSkin() {
        return VanillaLabelSkin.INSTANCE;
    }
}
