package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.AbstractGuiView;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class Label extends Node<Label.GuiLabelView> {

    public Label(String text) {
        this(text, 0xffffff);
    }

    public Label(String text, int color) {
        super(new GuiLabelView(text, color));
        this.computeSize();
        this.updateSize();
    }

    public Label(String text, Color color) {
        this(text, color.getRGB());
    }

    public String getText() {
        return this.getView().text;
    }

    public void setText(String text) {
        if (!this.getView().text.equals(text)) {
            this.getView().text = text;
            computeWidth();
            updateWidth();
        }
    }

    public int getColor() {
        return this.getView().color;
    }

    public void setColor(int color) {
        this.getView().color = color;
    }

    public void setColor(Color color) {
        this.setColor(color.getRGB());
    }

    public boolean isCentered() {
        return this.getView().centered;
    }

    public void setCentered(boolean centered) {
        this.getView().centered = centered;
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(mc.fontRenderer.getStringWidth(this.getView().text) + this.getPadding().getVertical());
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(mc.fontRenderer.FONT_HEIGHT + this.getPadding().getHorizontal());
    }

    public static class GuiLabelView extends AbstractGuiView {

        private String text;
        private boolean centered;
        private int color;

        public GuiLabelView(String text, int color) {
            this.text = text;
            this.color = color;
        }

        @Override
        public void renderView(int mouseX, int mouseY, float partialTicks) {
            if (this.visible) {
                GlStateManager.enableBlend();
                GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                int i = this.y + this.height / 2 - 4;
                if (this.centered) {
                    this.drawCenteredString(mc.fontRenderer, text, this.x + this.width / 2, i, this.color);
                } else {
                    this.drawString(mc.fontRenderer, text, this.x, i, this.color);
                }
            }
        }

    }
}
