package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;

public class ForgeVanillaTextFieldRenderer extends TextFieldWidget implements ForgeVanillaDelegateRenderer {
    private final TextField node;

    public ForgeVanillaTextFieldRenderer(TextField node) {
        super(Minecraft.getInstance().fontRenderer, node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabelComponent());
        this.node = node;
        active = !node.isDisabled();
        setMaxStringLength(node.getMaxLength());
        setText(node.getText());
        setCursorPositionZero(); // fix in order to render text
        setFocused(node.isFocused());
        setResponder(node::setText);
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.disabledProperty().addListener(newVal -> active = !newVal);
        node.<ITextComponent>labelComponentProperty().addListener(this::setMessage);
        node.maxLengthProperty().addListener(this::setMaxStringLength);
        node.textProperty().addListener(this::setText);
        node.focusedProperty().addListener(this::setFocused);
    }
}
