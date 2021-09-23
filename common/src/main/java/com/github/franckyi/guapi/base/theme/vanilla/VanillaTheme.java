package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.theme.Theme;
import com.github.franckyi.guapi.api.util.NodeType;
import com.github.franckyi.guapi.base.theme.AbstractTheme;

public final class VanillaTheme extends AbstractTheme {
    public static final Theme INSTANCE = new VanillaTheme();

    private VanillaTheme() {
        registerSkinInstance(NodeType.LABEL, VanillaLabelSkin.INSTANCE);
        registerSkinInstance(NodeType.HBOX, VanillaHBoxSkin.INSTANCE);
        registerSkinInstance(NodeType.VBOX, VanillaVBoxSkin.INSTANCE);
        registerSkinInstance(NodeType.IMAGE_VIEW, VanillaImageViewSkin.INSTANCE);
        registerSkinInstance(NodeType.ITEM_VIEW, VanillaItemViewSkin.INSTANCE);
        registerSkinInstance(NodeType.SPRITE_VIEW, VanillaSpriteViewSkin.INSTANCE);
        registerSkinSupplier(NodeType.BUTTON, VanillaButtonSkin::new);
        registerSkinSupplier(NodeType.TEXTURED_BUTTON, VanillaTexturedButtonSkin::new);
        registerSkinSupplier(NodeType.ENUM_BUTTON, VanillaEnumButtonSkin::new);
        registerSkinSupplier(NodeType.TEXT_FIELD, VanillaTextFieldSkin::new);
        registerSkinSupplier(NodeType.CHECK_BOX, VanillaCheckBoxSkin::new);
        registerSkinSupplier(NodeType.LIST_VIEW, VanillaListViewSkin::new);
        registerSkinSupplier(NodeType.TREE_VIEW, VanillaTreeViewSkin::new);
        registerSkinSupplier(NodeType.TOGGLE_BUTTON, VanillaToggleButtonSkin::new);
        registerSkinSupplier(NodeType.TEXTURED_TOGGLE_BUTTON, VanillaTexturedToggleButtonSkin::new);
        registerSkinSupplier(NodeType.SLIDER, VanillaSliderSkin::new);
        registerSkinSupplier(NodeType.TEXT_AREA, VanillaTextAreaSkin::new);
    }
}
