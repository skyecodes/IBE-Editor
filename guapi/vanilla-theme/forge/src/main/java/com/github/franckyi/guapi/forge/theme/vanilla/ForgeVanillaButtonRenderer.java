package com.github.franckyi.guapi.forge.theme.vanilla;

import com.github.franckyi.guapi.api.node.Button;
import net.minecraft.util.text.ITextComponent;

public class ForgeVanillaButtonRenderer extends net.minecraft.client.gui.widget.button.Button implements ForgeVanillaDelegateRenderer {
    public ForgeVanillaButtonRenderer(Button node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), (ITextComponent) node.getLabel(), button -> {
        });
        initLabeled(node, this);
    }
}
