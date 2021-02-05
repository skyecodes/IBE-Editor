package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.HBox;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.VBox;
import com.github.franckyi.guapi.theme.AbstractTheme;
import com.github.franckyi.guapi.theme.Theme;

public class VanillaTheme extends AbstractTheme {
    public static final Theme INSTANCE = new VanillaTheme();

    private VanillaTheme() {
        register(Label.class, VanillaLabelSkin.INSTANCE);
        register(HBox.class, VanillaHBoxSkin.INSTANCE);
        register(VBox.class, VanillaVBoxSkin.INSTANCE);
        register(Button.class, VanillaButtonSkin.INSTANCE);
    }
}
