package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiLabel;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Label extends Node<Label.GuiLabelView> {

    protected static final FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
    private String lazyText;
    private int lazyColor;
    private boolean lazyCentered;

    public Label(String text) {
        this(text, 0xffffff);
    }

    public Label(String text, int color) {
        super(new GuiLabelView(text, color));
        lazyText = text;
        lazyColor = color;
        this.computeSize();
        this.updateSize();
    }

    public Label(String text, Color color) {
        this(text, color.getRGB());
    }

    public String getText() {
        return lazyText;
    }

    public void setText(String text) {
        if (!lazyText.equals(text)) {
            try {
                this.getView().setText(text);
                this.lazyText = text;
                computeWidth();
                updateWidth();
            } catch (ObfuscationReflectionHelper.UnableToFindFieldException | ObfuscationReflectionHelper.UnableToAccessFieldException e) {
                e.printStackTrace();
            }

        }
    }

    public int getColor() {
        return lazyColor;
    }

    public void setColor(int color) {
        if (lazyColor != color) {
            try {
                this.getView().setColor(color);
                this.lazyColor = color;
            } catch (ObfuscationReflectionHelper.UnableToFindFieldException | ObfuscationReflectionHelper.UnableToAccessFieldException e) {
                e.printStackTrace();
            }

        }
    }

    public void setColor(Color color) {
        this.setColor(color.getRGB());
    }

    public boolean isCentered() {
        return lazyCentered;
    }

    public void setCentered(boolean centered) {
        if (lazyCentered != centered) {
            this.lazyCentered = centered;
            this.getView().setCentered(centered);
        }
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(fontRenderer.getStringWidth(this.lazyText) + this.getPadding().getVertical());
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(fontRenderer.FONT_HEIGHT + this.getPadding().getHorizontal());
    }

    public static class GuiLabelView extends GuiLabel implements Node.GuiView {

        public GuiLabelView(String text, int color) {
            super(new ArrayList<>(Collections.singletonList(text)), color, Minecraft.getInstance().fontRenderer);
            this.setVisible(true);
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }

        @Override
        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public boolean isVisible() {
            return visible;
        }

        @Override
        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public void setColor(int color) {
            ObfuscationReflectionHelper.setPrivateValue(GuiLabel.class, this, color, "field_146168_n");
        }

        public void setCentered(boolean centered) {
            ObfuscationReflectionHelper.setPrivateValue(GuiLabel.class, this, centered, "field_146170_l");
        }

        @SuppressWarnings("unchecked")
        public void setText(String text) {
            ((List<String>) ObfuscationReflectionHelper.getPrivateValue(GuiLabel.class, this, "field_146173_k")).set(0, text);
        }

    }
}
