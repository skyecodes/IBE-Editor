package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.node.HBox;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.VBox;
import com.github.franckyi.guapi.theme.Skin;
import com.github.franckyi.guapi.theme.Theme;

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
