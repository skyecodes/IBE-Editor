package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.impl.theme.AbstractTheme;
import com.github.franckyi.guapi.util.NodeType;

public class VanillaTheme extends AbstractTheme {
    public static final AbstractTheme INSTANCE = new VanillaTheme();

    private VanillaTheme() {
        registerSkinInstance(NodeType.LABEL, VanillaLabelSkin.INSTANCE);
        registerSkinInstance(NodeType.HBOX, VanillaHBoxSkin.INSTANCE);
        registerSkinInstance(NodeType.VBOX, VanillaVBoxSkin.INSTANCE);
        registerSkinInstance(NodeType.IMAGE_VIEW, VanillaImageViewSkin.INSTANCE);
        delegateSkinRenderer(NodeType.BUTTON, VanillaButtonSkin::new);
        delegateSkinRenderer(NodeType.TEXTURED_BUTTON, VanillaTexturedButtonSkin::new);
        delegateSkinRenderer(NodeType.ENUM_BUTTON, VanillaEnumButtonSkin::new);
        delegateSkinRenderer(NodeType.TEXT_FIELD, VanillaTextFieldSkin::new);
        delegateSkinRenderer(NodeType.CHECK_BOX, VanillaCheckBoxSkin::new);
        delegateSkinRenderer(NodeType.LIST_VIEW, VanillaListViewSkin::new);
        delegateSkinRenderer(NodeType.TREE_VIEW, VanillaTreeViewSkin::new);
        delegateSkinRenderer(NodeType.TOGGLE_BUTTON, VanillaToggleButtonSkin::new);
        delegateSkinRenderer(NodeType.TEXTURED_TOGGLE_BUTTON, VanillaTexturedToggleButtonSkin::new);
    }
}
