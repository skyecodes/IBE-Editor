package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import net.minecraft.client.gui.widget.button.CheckboxButton;

public class ForgeVanillaCheckBoxRenderer extends CheckboxButton implements ForgeVanillaDelegateRenderer {
    public ForgeVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get(), node.isChecked());
        initLabeled(node, this);
        node.checkedProperty().addListener(this::onPress);
    }
}
