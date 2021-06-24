package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import net.minecraft.client.gui.widget.CheckboxWidget;

public class FabricVanillaCheckBoxRenderer extends CheckboxWidget implements FabricVanillaDelegateRenderer {
    public FabricVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get(), node.isChecked());
        initLabeled(node, this);
        node.checkedProperty().addListener(this::onPress);
    }
}
