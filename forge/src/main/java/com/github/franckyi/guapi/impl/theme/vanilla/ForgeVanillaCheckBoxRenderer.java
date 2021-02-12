package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.util.text.ITextComponent;

public class ForgeVanillaCheckBoxRenderer extends CheckboxButton implements ForgeVanillaDelegateRenderer {
    private final CheckBox node;

    public ForgeVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabelComponent(), node.isChecked());
        this.node = node;
        active = !node.isDisabled();
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.<ITextComponent>labelComponentProperty().addListener(this::setMessage);
        node.checkedProperty().addListener(this::onPress);
        node.disabledProperty().addListener(newVal -> active = !newVal);
    }
}
