package com.github.franckyi.guapi.hooks.impl.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.hooks.api.theme.vanilla.VanillaDelegatedRenderer;
import com.github.franckyi.guapi.node.TextField;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class FabricVanillaTextFieldRenderer extends TextFieldWidget implements VanillaDelegatedRenderer<MatrixStack> {
    private final TextField node;

    public FabricVanillaTextFieldRenderer(TextField node) {
        super(MinecraftClient.getInstance().textRenderer, node.getX(), node.getY(), node.getWidth(), node.getHeight(), LiteralText.EMPTY);
        this.node = node;
        active = !node.isDisabled();
        setMaxLength(node.getMaxLength());
        setText(node.getText());
        setCursorToStart(); // fix in order to render text
        setFocused(node.isFocused());
        setChangedListener(node::setText);
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.disabledProperty().addListener(newVal -> active = !newVal);
        node.maxLengthProperty().addListener(this::setMaxLength);
        node.textProperty().addListener(this::setText);
        node.focusedProperty().addListener(this::setFocused);
    }

    @Override
    public void render(RenderContext<MatrixStack> ctx) {
        render(ctx.getMatrices(), ctx.getMouseX(), ctx.getMouseY(), ctx.getDelta());
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }
}
