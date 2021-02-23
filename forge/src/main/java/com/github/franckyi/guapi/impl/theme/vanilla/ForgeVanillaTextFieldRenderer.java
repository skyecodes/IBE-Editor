package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.gamehooks.impl.client.ForgeRenderer;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import com.github.franckyi.guapi.util.Color;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;

import java.util.Objects;

public class ForgeVanillaTextFieldRenderer extends TextFieldWidget implements ForgeVanillaDelegateRenderer {
    private final TextField node;

    public ForgeVanillaTextFieldRenderer(TextField node) {
        super(Minecraft.getInstance().fontRenderer, node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabelComponent());
        this.node = node;
        setEnabled(!node.isDisabled());
        setMaxStringLength(node.getMaxLength());
        setText(node.getText());
        setCursorPositionZero(); // fix in order to render text
        setFocused(node.isFocused());
        setResponder(node::setText);
        setValidator(node.getValidator());
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.disabledProperty().addListener(newVal -> setEnabled(!newVal));
        node.<ITextComponent>labelComponentProperty().addListener(this::setMessage);
        node.maxLengthProperty().addListener(this::setMaxStringLength);
        node.textProperty().addListener(newVal -> {
            int cursor = getCursorPosition();
            setText(newVal);
            setCursorPosition(cursor);
        });
        node.validatorProperty().addListener(this::updateValidator);
        node.validationForcedProperty().addListener(this::updateValidator);
        updateValidator();
    }

    private void updateValidator() {
        if (node.isValidationForced()) {
            if (node.getValidator() == null) {
                setValidator(Objects::nonNull);
            } else {
                setValidator(node.getValidator());
            }
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (!(node.isValidationForced() || node.getValidator().test(getText()))) {
            ForgeRenderer.INSTANCE.drawRectangle(matrixStack, x, y, x + width, y + height, Color.rgba(1, 0, 0, 0.5));
        }
    }
}
