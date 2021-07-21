package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.api.theme.Theme;
import com.github.franckyi.guapi.api.util.NodeType;
import com.github.franckyi.guapi.base.theme.AbstractTheme;
import com.github.franckyi.guapi.theme.vanilla.base.*;

public class VanillaTheme extends AbstractTheme {
    public static final Theme INSTANCE = new VanillaTheme();

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
