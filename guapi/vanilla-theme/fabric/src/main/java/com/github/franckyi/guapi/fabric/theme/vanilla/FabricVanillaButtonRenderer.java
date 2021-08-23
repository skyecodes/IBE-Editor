package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.guapi.api.node.Button;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class FabricVanillaButtonRenderer extends ButtonWidget implements FabricVanillaDelegateRenderer {
    public FabricVanillaButtonRenderer(Button node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), (Text) node.getLabel(), button -> {
        });
        initLabeled(node, this);
    }
}
