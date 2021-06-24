package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;

public class FabricVanillaButtonRenderer extends ButtonWidget implements FabricVanillaDelegateRenderer {
    public FabricVanillaButtonRenderer(Button node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get(), button -> {
        });
        initLabeled(node, this);
    }
}
