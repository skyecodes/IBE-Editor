package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.guapi.api.theme.Theme;
import com.github.franckyi.guapi.base.theme.AbstractTheme;

public final class VanillaTheme extends AbstractTheme {
    public static final Theme INSTANCE = new VanillaTheme();

    private VanillaTheme() {
        registerSkinInstance(Label.class, VanillaLabelSkin.INSTANCE);
        registerSkinInstance(HBox.class, VanillaHBoxSkin.INSTANCE);
        registerSkinInstance(VBox.class, VanillaVBoxSkin.INSTANCE);
        registerSkinInstance(ImageView.class, VanillaImageViewSkin.INSTANCE);
        registerSkinInstance(ItemView.class, VanillaItemViewSkin.INSTANCE);
        registerSkinInstance(SpriteView.class, VanillaSpriteViewSkin.INSTANCE);
        registerSkinSupplier(Button.class, VanillaButtonSkin::new);
        registerSkinSupplier(TexturedButton.class, VanillaTexturedButtonSkin::new);
        this.<EnumButton<?>>registerGenericSkinSupplier(EnumButton.class, VanillaEnumButtonSkin::new);
        registerSkinSupplier(TextField.class, VanillaTextFieldSkin::new);
        registerSkinSupplier(CheckBox.class, VanillaCheckBoxSkin::new);
        registerGenericSkinSupplier(ListView.class, VanillaListViewSkin::new);
        registerGenericSkinSupplier(TreeView.class, VanillaTreeViewSkin::new);
        registerSkinSupplier(ToggleButton.class, VanillaToggleButtonSkin::new);
        registerSkinSupplier(TexturedToggleButton.class, VanillaTexturedToggleButtonSkin::new);
        registerSkinSupplier(Slider.class, VanillaSliderSkin::new);
        registerSkinSupplier(TextArea.class, VanillaTextAreaSkin::new);
    }
}
