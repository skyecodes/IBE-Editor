package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;

public class ForgeVanillaButtonRenderer extends net.minecraft.client.gui.widget.button.Button implements ForgeVanillaDelegateRenderer {
    public ForgeVanillaButtonRenderer(Button node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get(), button -> {
        });
        active = !node.isDisabled();
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.labelProperty().addListener(label -> setMessage(label.get()));
        node.disabledProperty().addListener(newVal -> active = !newVal);
    }
}
