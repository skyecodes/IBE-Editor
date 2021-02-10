package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.impl.theme.AbstractTheme;
import com.github.franckyi.guapi.util.NodeType;

public class VanillaTheme extends AbstractTheme {
    public static final AbstractTheme INSTANCE = new VanillaTheme();

    private VanillaTheme() {
        registerSkinInstance(NodeType.LABEL, VanillaLabelSkin.INSTANCE);
        registerSkinInstance(NodeType.HBOX, VanillaHBoxSkin.INSTANCE);
        registerSkinInstance(NodeType.VBOX, VanillaVBoxSkin.INSTANCE);
        registerSkinInstance(NodeType.WEIGHTED_HBOX, VanillaWeightedHBoxSkin.INSTANCE);
        registerSkinInstance(NodeType.WEIGHTED_VBOX, VanillaWeightedVBoxSkin.INSTANCE);
        delegateSkinRenderer(NodeType.BUTTON, VanillaButtonSkin::new);
        delegateSkinRenderer(NodeType.TEXTFIELD, VanillaTextFieldSkin::new);
        delegateSkinRenderer(NodeType.CHECKBOX, VanillaCheckBoxSkin::new);
        delegateSkinRenderer(NodeType.LISTVIEW, VanillaListViewSkin::new);
    }
}
