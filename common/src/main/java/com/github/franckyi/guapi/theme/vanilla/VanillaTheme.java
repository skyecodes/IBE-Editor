package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.node.*;
import com.github.franckyi.guapi.theme.AbstractTheme;

public class VanillaTheme extends AbstractTheme {
    public static final AbstractTheme INSTANCE = new VanillaTheme();

    private VanillaTheme() {
        registerSkinInstance(Label.class, VanillaLabelSkin.INSTANCE);
        registerSkinInstance(HBox.class, VanillaHBoxSkin.INSTANCE);
        registerSkinInstance(VBox.class, VanillaVBoxSkin.INSTANCE);
        registerSkinInstance(WeightedHBox.class, VanillaWeightedHBoxSkin.INSTANCE);
        registerSkinInstance(WeightedVBox.class, VanillaWeightedVBoxSkin.INSTANCE);
        delegateSkinRenderer(Button.class, VanillaButtonSkin::new);
        delegateSkinRenderer(TextField.class, VanillaTextFieldSkin::new);
        delegateSkinRenderer(CheckBox.class, VanillaCheckBoxSkin::new);
        delegateSkinRenderer(ListView.class, VanillaListViewSkin::new);
    }
}
