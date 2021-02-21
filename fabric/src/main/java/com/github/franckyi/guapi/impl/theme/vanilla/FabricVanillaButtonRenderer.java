package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class FabricVanillaButtonRenderer extends ButtonWidget implements FabricVanillaDelegateRenderer {
    public FabricVanillaButtonRenderer(Button node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabelComponent(), button -> {
        });
        active = !node.isDisabled();
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.<Text>labelComponentProperty().addListener(this::setMessage);
        node.disabledProperty().addListener(newVal -> active = !newVal);
    }
}
