package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.node.*;
import com.github.franckyi.guapi.theme.AbstractTheme;

public class VanillaTheme extends AbstractTheme {
    public static final AbstractTheme INSTANCE = new VanillaTheme();

    private VanillaTheme() {
        registerSkinInstance(Label.class, VanillaLabelSkin.INSTANCE);
        registerSkinInstance(HBox.class, VanillaHBoxSkin.INSTANCE);
        registerSkinInstance(VBox.class, VanillaVBoxSkin.INSTANCE);
        delegateSkinProvider(Button.class, VanillaButtonSkin::new);
        delegateSkinProvider(TextField.class, VanillaTextFieldSkin::new);
        delegateSkinProvider(CheckBox.class, VanillaCheckBoxSkin::new);
    }
}
