package com.github.franckyi.guapi.hooks.impl.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.hooks.api.theme.vanilla.VanillaNodeRenderer;
import com.github.franckyi.guapi.node.TextField;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.StringTextComponent;

public class ForgeVanillaTextFieldRenderer extends TextFieldWidget implements VanillaNodeRenderer<MatrixStack> {
    private final TextField node;

    ForgeVanillaTextFieldRenderer(TextField node) {
        super(Minecraft.getInstance().fontRenderer, node.getX(), node.getY(), node.getWidth(), node.getHeight(), StringTextComponent.EMPTY);
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
        node.maxLengthProperty().addListener(this::setMaxStringLength);
        node.textProperty().addListener(this::setText);
        node.focusedProperty().addListener(this::setFocused);
    }

    @Override
    public void render(RenderContext<MatrixStack> ctx) {
        render(ctx.getMatrices(), ctx.getMouseX(), ctx.getMouseY(), ctx.getDelta());
    }

    @Override
    public void mouseMoved(double xPos, double mouseY) {
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }
}
