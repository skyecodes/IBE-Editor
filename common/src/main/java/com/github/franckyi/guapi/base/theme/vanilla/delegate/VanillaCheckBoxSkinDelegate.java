package com.github.franckyi.guapi.base.theme.vanilla.delegate;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class VanillaCheckBoxSkinDelegate extends Checkbox implements VanillaWidgetSkinDelegate {
    private static final ResourceLocation TEXTURE = new ResourceLocation("ibeeditor", "textures/gui/checkbox.png");
    protected final CheckBox node;

    public VanillaCheckBoxSkinDelegate(CheckBox node) {
        super(node.getX(), node.getY(), node.getLabel(), Minecraft.getInstance().font, node.isChecked(), (c, b) -> node.setChecked(b));
        this.node = node;
        initLabeledWidget(node);
        node.checkedProperty().addListener(this::onModelChange);
    }

    private void onModelChange() {
        if (node.isChecked() != selected()) {
            super.onPress();
        }
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        Minecraft mc = Minecraft.getInstance();
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        guiGraphics.blit(TEXTURE, getX(), getY(), isHoveredOrFocused() ? 16 : 0, selected() ? 16 : 0, 16, 16, 32, 32);
        guiGraphics.drawString(mc.font, getMessage(), getX() + 20, getY() + (height - mc.font.lineHeight - 1) / 2, 14737632 | Mth.ceil(alpha * 255.0F) << 24);
    }
}
