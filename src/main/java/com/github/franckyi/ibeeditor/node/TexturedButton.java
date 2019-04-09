package com.github.franckyi.ibeeditor.node;

import com.github.franckyi.guapi.node.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class TexturedButton extends Button {

    public TexturedButton(String filename) {
        this(new ResourceLocation("ibeeditor:textures/gui/" + filename));
    }

    public TexturedButton(String filename, String text) {
        this(new ResourceLocation("ibeeditor:textures/gui/" + filename), text);
    }

    public TexturedButton(ResourceLocation texture) {
        this(texture, "");
    }

    public TexturedButton(ResourceLocation texture, String text) {
        super(new GuiTexturedButtonView(texture, text));
    }

    @Override
    public GuiTexturedButtonView getView() {
        return (GuiTexturedButtonView) super.getView();
    }

    @Override
    public String getText() {
        return this.getView().tooltipText;
    }

    @Override
    public void setText(String text) {
        this.getView().tooltipText = text;
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(20);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(20);
    }

    public static class GuiTexturedButtonView extends Button.GuiButtonView {

        private final ResourceLocation texture;
        private String tooltipText;

        public GuiTexturedButtonView(ResourceLocation texture, String text) {
            super("");
            this.texture = texture;
            this.tooltipText = text;
        }

        @Override
        public void render(int mouseX, int mouseY, float partialTicks) {
            super.render(mouseX, mouseY, partialTicks);
            if (this.visible) {
                Minecraft mc = Minecraft.getInstance();
                mc.getTextureManager().bindTexture(texture);
                this.drawModalRectWithCustomSizedTexture(this.x + 2, this.y + 2, 0, 0, 16, 16, 16, 16, 2);
                if (this.hovered && !tooltipText.isEmpty()) {
                    mc.currentScreen.drawHoveringText(tooltipText, mouseX, mouseY);
                }
            }
        }

        private void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height,
                                                         float textureWidth, float textureHeight, double zLevel) {
            float f = 1.0F / textureWidth;
            float f1 = 1.0F / textureHeight;
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder vertexbuffer = tessellator.getBuffer();
            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
            vertexbuffer.pos((double) x, (double) (y + height), zLevel)
                    .tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
            vertexbuffer.pos((double) (x + width), (double) (y + height), zLevel)
                    .tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
            vertexbuffer.pos((double) (x + width), (double) y, zLevel)
                    .tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
            vertexbuffer.pos((double) x, (double) y, zLevel).tex((double) (u * f), (double) (v * f1)).endVertex();
            tessellator.draw();
        }

    }
}
