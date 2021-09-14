package com.github.franckyi.guapi.forge.theme.vanilla;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class ForgeVanillaCheckBoxRenderer extends Checkbox implements ForgeVanillaDelegateRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation("guapivanillatheme", "textures/gui/checkbox.png");
    private final CheckBox node;

    public ForgeVanillaCheckBoxRenderer(CheckBox node) {
        super(node.getX(), node.getY(), node.getWidth(), node.getHeight(), (Component) node.getLabel(), node.isChecked());
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
    public void renderButton(@Nonnull PoseStack matrices, int mouseX, int mouseY, float delta) {
        Minecraft mc = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        blit(matrices, x, y, isHovered() ? 16 : 0, selected() ? 16 : 0, 16, 16, 32, 32);
        renderBg(matrices, mc, mouseX, mouseY);
        drawString(matrices, mc.font, getMessage(), x + 20, y + (height - mc.font.lineHeight - 1) / 2, 14737632 | Mth.ceil(alpha * 255.0F) << 24);
    }
}
