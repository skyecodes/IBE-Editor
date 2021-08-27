package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.EnumButton;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class FabricVanillaButtonRenderer extends ButtonWidget implements FabricVanillaDelegateRenderer {
    private final boolean isEnumButton;

    public FabricVanillaButtonRenderer(Button node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), (Text) node.getLabel(), button -> {
        });
        initLabeled(node, this);
        isEnumButton = node instanceof EnumButton;
    }

    @Override
    protected boolean isValidClickButton(int button) {
        return super.isValidClickButton(button) || (isEnumButton && button == 1);
    }
}
