package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.theme.vanilla.ForgeVanillaDelegateRenderer;
import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.minecraft.api.client.render.Matrices;
import com.github.franckyi.minecraft.impl.client.render.ForgeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;

import java.util.Objects;

public class ForgeVanillaTextFieldRenderer extends TextFieldWidget implements ForgeVanillaDelegateRenderer {
    private final TextField node;

    public ForgeVanillaTextFieldRenderer(TextField node) {
        super(Minecraft.getInstance().fontRenderer, node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().getComponent());
        this.node = node;
        setEnabled(!node.isDisabled());
        setMaxStringLength(node.getMaxLength());
        setText(node.getText());
        setCursorPositionZero(); // fix in order to render text
        setFocused(node.isFocused());
        setResponder(node::setText);
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.disabledProperty().addListener(newVal -> setEnabled(!newVal));
        node.labelProperty().addListener(label -> setMessage(label.getComponent()));
        node.maxLengthProperty().addListener(this::setMaxStringLength);
        node.textProperty().addListener(newVal -> {
            int cursor = getCursorPosition();
            setText(newVal);
            setCursorPosition(cursor);
        });
        node.focusedProperty().addListener(this::setFocused);
        node.validatorProperty().addListener(this::updateValidator);
        node.validationForcedProperty().addListener(this::updateValidator);
        node.textRendererProperty().addListener(this::updateRenderer);
        updateValidator();
        updateRenderer();
    }

    private void updateValidator() {
        if (node.isValidationForced()) {
            if (node.getValidator() == null) {
                setValidator(Objects::nonNull);
            } else {
                setValidator(node.getValidator());
            }
        } else {
            setValidator(Objects::nonNull);
        }
    }

    private void updateRenderer() {
        if (node.getTextRenderer() == null) {
            setTextFormatter((string, integer) -> IReorderingProcessor.fromString(string, Style.EMPTY));
        } else {
            setTextFormatter((string, integer) -> ((ITextComponent) node.getTextRenderer().render(string, integer).getComponent()).func_241878_f());
        }
        setCursorPositionZero(); // fix in order to render text
    }

    @Override
    public void render(Matrices matrices, int mouseX, int mouseY, float partialTicks) {
        ForgeVanillaDelegateRenderer.super.render(matrices, mouseX, mouseY, partialTicks);
        if (!(node.isValidationForced() || node.getValidator().test(getText()))) {
            ForgeRenderer.INSTANCE.drawRectangle(matrices, x - 1, y - 1, x + width + 1, y + height + 1, Color.rgba(1, 0, 0, 0.8));
        }
    }
}
