package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Scene;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class TexturedButton extends Node<TexturedButton.GuiGraphicButtonView> {

    public TexturedButton(String filename) {
        this(new ResourceLocation(IBEEditorMod.MODID, "textures/gui/" + filename));
    }

    public TexturedButton(String filename, String text) {
        this(new ResourceLocation(IBEEditorMod.MODID, "textures/gui/" + filename), text);
    }

    public TexturedButton(ResourceLocation texture) {
        this(texture, "");
        this.getView().getText().clear();
    }

    public TexturedButton(ResourceLocation texture, String text) {
        super(new GuiTexturedButtonView(texture, text));
        this.computeSize();
        this.updateSize();
    }

    public TexturedButton(ItemStack item) {
        super(new GuiItemTexturedButtonView(item));
        this.computeSize();
        this.updateSize();
    }

    @Override
    public GuiGraphicButtonView getView() {
        return super.getView();
    }

    public boolean isDisabled() {
        return !this.getView().enabled;
    }

    public void setDisabled(boolean disabled) {
        this.getView().enabled = !disabled;
    }

    public List<String> getText() {
        return this.getView().getText();
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(20);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(20);
    }

    public static abstract class GuiGraphicButtonView extends Button.GuiButtonView {

        public GuiGraphicButtonView(String... text) {
            super("", text);
        }

        public List<String> getText() {
            return Collections.emptyList();
        }

    }

    public static class GuiTexturedButtonView extends GuiGraphicButtonView {

        private ResourceLocation texture;

        public GuiTexturedButtonView(ResourceLocation texture, String... text) {
            super(text);
            this.texture = texture;
        }

        public ResourceLocation getTexture() {
            return texture;
        }

        public void setTexture(ResourceLocation texture) {
            this.texture = texture;
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
        public List<String> getText() {
            return tooltipText;
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
                int x = this.x + 2;
                int y = this.y + 2;
                mc.getItemRenderer().renderItemAndEffectIntoGUI(item, x, y);
                mc.getItemRenderer().renderItemOverlays(mc.fontRenderer, item, x, y);
                if (this.hovered) {
                    if (screen == null) {
                        screen = (Scene.Screen) mc.currentScreen;
                    }
                    screen.renderToolTip(item, mouseX, mouseY);
                }
            }
        }
    }
}
