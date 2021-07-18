package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import net.minecraft.client.gui.widget.button.CheckboxButton;

public class ForgeVanillaCheckBoxRenderer extends CheckboxButton implements ForgeVanillaDelegateRenderer {
    private final CheckBox node;

    public ForgeVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get(), node.isChecked());
        this.node = node;
        initLabeled(node, this);
        node.checkedProperty().addListener(this::onModelChange);
    }

    private void onModelChange() {
        if (node.isChecked() != selected()) {
            super.onPress();
        }
    }

    @Override
    public void onPress() {
        super.onPress();
        node.setChecked(selected());
    }
}
