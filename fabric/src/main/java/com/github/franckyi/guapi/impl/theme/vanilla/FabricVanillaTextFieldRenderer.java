package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.theme.vanilla.FabricVanillaDelegateRenderer;
import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.minecraft.api.client.render.Matrices;
import com.github.franckyi.minecraft.impl.client.render.FabricRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.Objects;

public class FabricVanillaTextFieldRenderer extends TextFieldWidget implements FabricVanillaDelegateRenderer {
    private final TextField node;

    public FabricVanillaTextFieldRenderer(TextField node) {
        super(MinecraftClient.getInstance().textRenderer, node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get());
        this.node = node;
        setEditable(!node.isDisabled());
        setMaxLength(node.getMaxLength());
        setText(node.getText());
        setCursorToStart(); // fix in order to render text
        setFocused(node.isFocused());
        setChangedListener(node::setText);
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.disabledProperty().addListener(newVal -> setEditable(!newVal));
        node.labelProperty().addListener(label -> setMessage(label.get()));
        node.maxLengthProperty().addListener(this::setMaxLength);
        node.textProperty().addListener(newVal -> {
            int cursor = getCursor();
            setText(newVal);
            setCursor(cursor);
        });
        node.focusedProperty().addListener(this::setFocused);
        node.validatorProperty().addListener(this::updateValidator);
        node.validationForcedProperty().addListener(this::updateValidator);
        updateValidator();
    }

    private void updateValidator() {
        if (node.isValidationForced()) {
            if (node.getValidator() == null) {
                setTextPredicate(Objects::nonNull);
            } else {
                setTextPredicate(node.getValidator());
            }
        }
    }

    @Override
    public void render(Matrices matrices, int mouseX, int mouseY, float delta) {
        FabricVanillaDelegateRenderer.super.render(matrices, mouseX, mouseY, delta);
        if (!(node.isValidationForced() || node.getValidator().test(getText()))) {
            FabricRenderer.INSTANCE.drawRectangle(matrices, x - 1, y - 1, x + width + 1, y + height + 1, Color.rgba(1, 0, 0, 0.8));
        }
    }
}
