package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;

public class FabricVanillaCheckBoxRenderer extends CheckboxWidget implements FabricVanillaDelegateRenderer {
    private final CheckBox node;

    public FabricVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabelComponent(), node.isChecked());
        this.node = node;
        active = !node.isDisabled();
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.<Text>labelComponentProperty().addListener(this::setMessage);
        node.checkedProperty().addListener(this::onPress);
        node.disabledProperty().addListener(newVal -> active = !newVal);
    }
}
