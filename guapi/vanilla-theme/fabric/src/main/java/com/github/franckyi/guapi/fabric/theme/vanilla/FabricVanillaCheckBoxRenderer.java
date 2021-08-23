package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class FabricVanillaCheckBoxRenderer extends CheckboxWidget implements FabricVanillaDelegateRenderer {
    private static final Identifier TEXTURE = new Identifier("guapi-vanilla-theme", "textures/gui/checkbox.png");
    private final CheckBox node;

    public FabricVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), (Text) node.getLabel(), node.isChecked());
        this.node = node;
        initLabeled(node, this);
        node.checkedProperty().addListener(this::onModelChange);
    }

    private void onModelChange() {
        if (node.isChecked() != isChecked()) {
            super.onPress();
        }
    }

    @Override
    public void onPress() {
        super.onPress();
        node.setChecked(isChecked());
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.getTextureManager().bindTexture(TEXTURE);
        RenderSystem.enableDepthTest();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        drawTexture(matrices, x, y, isHovered() ? 16 : 0, isChecked() ? 16 : 0, 16, 16, 32, 32);
        renderBackground(matrices, mc, mouseX, mouseY);
        drawTextWithShadow(matrices, mc.textRenderer, getMessage(), x + 20, y + (height - mc.textRenderer.fontHeight - 1) / 2, 14737632 | MathHelper.ceil(alpha * 255.0F) << 24);
    }
}
