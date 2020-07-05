package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Scene;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
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

    public TexturedButton(ResourceLocation texture, int width, int height, int u, int v) {
        this(texture, width, height, u, v, "");
        this.getView().getText().clear();
    }

    public TexturedButton(ResourceLocation texture, String text) {
        this(texture, 16, 16, 0, 0, text);
    }

    public TexturedButton(ResourceLocation texture, int width, int height, int u, int v, String text) {
        super(new GuiTexturedButtonView(texture, width, height, u, v, text));
        this.computeSize();
        this.updateSize();
    }

    public TexturedButton(ItemStack item) {
        this(item, true);
    }

    public TexturedButton(ItemStack item, boolean showTooltip) {
        super(new GuiItemTexturedButtonView(item, showTooltip));
        this.computeSize();
        this.updateSize();
    }

    public boolean isDisabled() {
        return !this.getView().active;
    }

    public void setDisabled(boolean disabled) {
        this.getView().active = !disabled;
    }

    public List<String> getText() {
        return this.getView().getText();
    }

    public List<String> getTooltipText() {
        return this.getView().tooltipText;
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
        private ResourceLocation resource;
        private int u, v;
        private int textureWidth, textureHeight;

        private ResourceLocation texture;
        private boolean flag;

        public GuiTexturedButtonView(ResourceLocation resource, String... text) {
            this(resource, 16, 16, 0, 0, text);
        }

        public GuiTexturedButtonView(ResourceLocation resource, int width, int height, int u, int v, String... text) {
            super(text);
            this.setResource(resource);
            this.setTextureSize(width, height);
            this.setTexturePosition(u, v);
        }

        public ResourceLocation getResource() {
            return resource;
        }

        public void setResource(ResourceLocation resource) {
            if (!resource.equals(this.resource)) {
                this.resource = resource;
                flag = true;
            }
        }

        public void setTexturePosition(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public void setTextureSize(int width, int height) {
            this.textureWidth = width;
            this.textureHeight = height;
        }

        @Override
        public void renderView(int mouseX, int mouseY, float partialTicks) {
            super.render(mouseX, mouseY, partialTicks);
            if (this.visible) {
                if (flag) {
                    texture = this.loadTexture();
                    flag = false;
                }
                mc.getTextureManager().bindTexture(texture);
                this.drawModalRectWithCustomSizedTexture(this.x + 2, this.y + 2, u, v, 16, 16, textureWidth, textureHeight, 2);
                if (this.isHovered() && !tooltipText.isEmpty()) {
                    mc.currentScreen.renderTooltip(tooltipText, mouseX, mouseY);
                }
            }
        }

        private ResourceLocation loadTexture() {
            SimpleTexture tex = new SimpleTexture(resource);
            try {
                tex.loadTexture(mc.getResourceManager());
                return resource;
            } catch (IOException e) {
                return TextureManager.RESOURCE_LOCATION_EMPTY;
            }
        }

        private void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height,
                                                         float textureWidth, float textureHeight, double zLevel) {
            float f = 1.0F / textureWidth;
            float f1 = 1.0F / textureHeight;
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder vertexbuffer = tessellator.getBuffer();
            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
            vertexbuffer.pos(x, y + height, zLevel)
                    .tex(u * f, (v + (float) height) * f1).endVertex();
            vertexbuffer.pos(x + width, y + height, zLevel)
                    .tex((u + (float) width) * f, (v + (float) height) * f1).endVertex();
            vertexbuffer.pos(x + width, y, zLevel)
                    .tex((u + (float) width) * f, v * f1).endVertex();
            vertexbuffer.pos(x, y, zLevel).tex(u * f, v * f1).endVertex();
            tessellator.draw();
        }

        @Override
        public List<String> getText() {
            return tooltipText;
        }

    }

    private static class GuiItemTexturedButtonView extends GuiGraphicButtonView {

        private final ItemStack item;
        private final boolean showTooltip;
        private Scene.GUAPIScreen screen;

        public GuiItemTexturedButtonView(ItemStack item, boolean showTooltip) {
            this.item = item;
            this.showTooltip = showTooltip;
        }

        @Override
        public void renderView(int mouseX, int mouseY, float partialTicks) {
            super.renderView(mouseX, mouseY, partialTicks);
            if (this.visible) {
                int x = this.x + 2;
                int y = this.y + 2;
                mc.getItemRenderer().renderItemAndEffectIntoGUI(item, x, y);
                mc.getItemRenderer().renderItemOverlays(mc.fontRenderer, item, x, y);
                if (this.isHovered() && this.showTooltip) {
                    if (screen == null) {
                        screen = (Scene.GUAPIScreen) mc.currentScreen;
                    }
                    screen.renderTooltip(item, mouseX, mouseY);
                }
            }
        }
    }
}
