package com.github.franckyi.guapi.hooks.impl.theme.vanilla;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.hooks.api.theme.vanilla.VanillaDelegatedRenderer;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.CheckBox;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class FabricVanillaCheckBoxRenderer extends CheckboxWidget implements VanillaDelegatedRenderer<MatrixStack> {
    private final CheckBox node;

    public FabricVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), new LiteralText(node.getText()), node.isChecked());
        this.node = node;
        active = !node.isDisabled();
        node.xProperty().addListener(newVal -> x = newVal);
        node.yProperty().addListener(newVal -> y = newVal);
        node.widthProperty().addListener(newVal -> width = newVal);
        node.heightProperty().addListener(newVal -> height = newVal);
        node.textProperty().addListener(newVal -> setMessage(new LiteralText(node.getText())));
        node.checkedProperty().addListener(this::onPress);
        node.disabledProperty().addListener(newVal -> active = !newVal);
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

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return false;
    }
}
