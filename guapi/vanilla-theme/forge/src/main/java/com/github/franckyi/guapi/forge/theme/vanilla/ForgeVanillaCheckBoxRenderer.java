package com.github.franckyi.guapi.forge.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ForgeVanillaCheckBoxRenderer extends CheckboxButton implements ForgeVanillaDelegateRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation("guapi-vanilla-theme", "textures/gui/checkbox.png");
    private final CheckBox node;

    public ForgeVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get(), node.isChecked());
        this.node = node;
        initLabeled(node, this);
        node.checkedProperty().addListener(this::onModelChange);
    }

    private void onModelChange() {
        if (node.isChecked() != selected()) {
            super.onPress();
        }
    }

    @Override
    public void onPress() {
        super.onPress();
        node.setChecked(selected());
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bind(TEXTURE);
        RenderSystem.enableDepthTest();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        blit(matrices, x, y, isHovered() ? 16 : 0, selected() ? 16 : 0, 16, 16, 32, 32);
        renderBg(matrices, mc, mouseX, mouseY);
        drawString(matrices, mc.font, getMessage(), x + 20, y + (height - mc.font.lineHeight - 1) / 2, 14737632 | MathHelper.ceil(alpha * 255.0F) << 24);
    }
}
