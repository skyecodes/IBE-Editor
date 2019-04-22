package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Scene;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TexturedButton extends Button {

    public TexturedButton(String filename) {
        this(new ResourceLocation(IBEEditorMod.MODID, "textures/gui/" + filename));
    }

    public TexturedButton(String filename, String text) {
        this(new ResourceLocation(IBEEditorMod.MODID, "textures/gui/" + filename), text);
    }

    public TexturedButton(ResourceLocation texture) {
        this(texture, "");
    }

    public TexturedButton(ResourceLocation texture, String text) {
        super(new GuiTexturedButtonView(texture, text));
    }

    public TexturedButton(ItemStack item) {
        super(new GuiItemTexturedButtonView(item));
    }

    @Override
    public GuiGraphicButtonView getView() {
        return (GuiGraphicButtonView) super.getView();
    }

    @Override
    public String getText() {
        return this.getView().getText();
    }

    @Override
    public void setText(String text) {
        this.getView().setText(text);
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(20);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(20);
    }

    public static abstract class GuiGraphicButtonView extends GuiButtonView {

        public GuiGraphicButtonView() {
            super("");
        }

        public abstract String getText();

        public abstract void setText(String text);

    }

    public static class GuiTexturedButtonView extends GuiGraphicButtonView {

        private final ResourceLocation texture;
        private String tooltipText;

        public GuiTexturedButtonView(ResourceLocation texture, String text) {
            this.texture = texture;
            this.tooltipText = text;
        }

        @Override
        public void renderView(int mouseX, int mouseY, float partialTicks) {
            super.render(mouseX, mouseY, partialTicks);
            if (this.visible) {
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

        @Override
        public String getText() {
            return tooltipText;
        }

        @Override
        public void setText(String text) {
            this.tooltipText = text;
        }
    }

    private static class GuiItemTexturedButtonView extends GuiGraphicButtonView {

        private final ItemStack item;
        private Scene.Screen screen;

        public GuiItemTexturedButtonView(ItemStack item) {
            this.item = item;
        }

        @Override
        public void renderView(int mouseX, int mouseY, float partialTicks) {
            super.renderView(mouseX, mouseY, partialTicks);
            if (this.visible) {
                mc.getItemRenderer().renderItemIntoGUI(item, x + 2, y + 2);
                if (this.hovered) {
                    if (screen == null) {
                        screen = (Scene.Screen) mc.currentScreen;
                    }
                    screen.renderToolTip(item, mouseX, mouseY);
                }
            }
        }

        @Override
        public String getText() {
            return "";
        }

        @Override
        public void setText(String text) {
        }
    }
}
