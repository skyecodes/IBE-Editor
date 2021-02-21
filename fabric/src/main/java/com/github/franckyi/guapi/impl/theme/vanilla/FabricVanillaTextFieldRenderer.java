package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class FabricVanillaTextFieldRenderer extends TextFieldWidget implements FabricVanillaDelegateRenderer {
    private final TextField node;

    public FabricVanillaTextFieldRenderer(TextField node) {
        super(MinecraftClient.getInstance().textRenderer, node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabelComponent());
        this.node = node;
        setEditable(!node.isDisabled());
        setMaxLength(node.getMaxLength());
        setText(node.getText());
        setCursorToStart(); // fix in order to render text
        setFocused(node.isFocused());
        setChangedListener(node::setText);
        setTextPredicate(node.getValidator());
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.disabledProperty().addListener(newVal -> setEditable(!newVal));
        node.<Text>labelComponentProperty().addListener(this::setMessage);
        node.maxLengthProperty().addListener(this::setMaxLength);
        node.textProperty().addListener(newVal -> {
            int cursor = getCursor();
            setText(newVal);
            setCursor(cursor);
        });
        node.focusedProperty().addListener(this::setFocused);
        node.validatorProperty().addListener(this::setTextPredicate);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (!this.isActive()) {
            return false;
        } else if (isValidChar(chr)) {
            if (!node.isDisabled()) {
                this.write(Character.toString(chr));
            }
            return true;
        } else {
            return false;
        }
    }

    protected boolean isValidChar(char chr) {
        return (node.isAllowFormattingChar() || chr != 167) && chr >= ' ' && chr != 127;
    }
}
