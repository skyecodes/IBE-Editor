package com.github.franckyi.guapi.common.theme.vanilla;

import com.github.franckyi.guapi.common.node.HBox;
import com.github.franckyi.guapi.common.node.Label;
import com.github.franckyi.guapi.common.node.VBox;
import com.github.franckyi.guapi.common.theme.Skin;
import com.github.franckyi.guapi.common.theme.Theme;

public class VanillaTheme implements Theme {
    public static final Theme INSTANCE = new VanillaTheme();

    @Override
    public Skin<Label> getLabelSkin() {
        return VanillaLabelSkin.INSTANCE;
    }

    @Override
    public Skin<HBox> getHBoxSkin() {
        return VanillaHBoxSkin.INSTANCE;
    }

    @Override
    public Skin<VBox> getVBoxSkin() {
        return VanillaVBoxSkin.INSTANCE;
    }
}
