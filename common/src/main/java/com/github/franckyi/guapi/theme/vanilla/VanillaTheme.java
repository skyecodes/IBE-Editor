package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.theme.vanilla.VanillaThemeRenderer;
import com.github.franckyi.guapi.node.*;
import com.github.franckyi.guapi.theme.AbstractTheme;
import com.github.franckyi.guapi.theme.Theme;

public class VanillaTheme extends AbstractTheme {
    public static Theme instance;

    private VanillaTheme(VanillaThemeRenderer renderer) {
        registerSkinProvider(Label.class, n -> VanillaLabelSkin.INSTANCE);
        registerSkinProvider(HBox.class, n -> VanillaHBoxSkin.INSTANCE);
        registerSkinProvider(VBox.class, n -> VanillaVBoxSkin.INSTANCE);
        registerSkinProvider(Button.class, n -> new VanillaButtonSkin(renderer.button(n)));
        registerSkinProvider(TextField.class, n -> new VanillaTextFieldSkin(renderer.textField(n)));
        //registerSkin(ListView.class, VanillaListViewSkin.INSTANCE);
    }

    public static Theme create(VanillaThemeRenderer renderer) {
        if (instance == null) {
            instance = new VanillaTheme(renderer);
        }
        return instance;
    }

    public static Theme instance() {
        if (instance == null) {
            throw new IllegalStateException("Theme is not initialized");
        }
        return instance;
    }
}
