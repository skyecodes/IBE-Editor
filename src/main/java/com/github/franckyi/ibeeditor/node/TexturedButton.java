package com.github.franckyi.ibeeditor.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.node.Button;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TexturedButton extends Node<Button.GuiButtonView> {

    public TexturedButton(String filename) {
        this(new ResourceLocation("ibeeditor:textures/gui/" + filename));
    }

    public TexturedButton(ResourceLocation texture) {
        super(new GuiTexturedButtonView(texture));
        this.computeSize();
        this.updateSize();
    }

    public TexturedButton(Item item) {
        super(new GuiItemButtonView(item));
        this.computeSize();
        this.updateSize();
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

        public GuiTexturedButtonView(ResourceLocation texture) {
            this.texture = texture;
        }

        @Override
        public void render(int mouseX, int mouseY, float partialTicks) {
            super.render(mouseX, mouseY, partialTicks);
            if (this.visible) {
                Minecraft.getInstance().getTextureManager().bindTexture(texture);
                this.drawModalRectWithCustomSizedTexture(this.x + 2, this.y + 2, 0, 0, 16, 16, 16, 16, 2);
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

    private static class GuiItemButtonView extends Button.GuiButtonView {

        private Item item;

        public GuiItemButtonView(Item item) {
            this.item = item;
        }

        @Override
        public void render(int mouseX, int mouseY, float partialTicks) {
            super.render(mouseX, mouseY, partialTicks);
            if (this.visible) {
                Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(new ItemStack(item), x + 2, y + 2);
            }
        }
    }
}
