package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import net.minecraft.client.gui.widget.CheckboxWidget;

public class FabricVanillaCheckBoxRenderer extends CheckboxWidget implements FabricVanillaDelegateRenderer {
    private final CheckBox node;

    public FabricVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get(), node.isChecked());
        this.node = node;
        initLabeled(node, this);
        node.checkedProperty().addListener(this::onModelChange);
    }

    private void onModelChange() {
        if (node.isChecked() != isChecked()) {
            super.onPress();
        }
    }

    @Override
    public void onPress() {
        super.onPress();
        node.setChecked(isChecked());
    }
}
