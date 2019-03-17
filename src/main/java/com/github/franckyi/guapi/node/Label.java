package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.GuiLabelView;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.awt.*;

public class Label extends Node {

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
    public GuiLabelView getView() {
        return (GuiLabelView) super.getView();
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(fontRenderer.getStringWidth(this.lazyText) + this.getPadding().getVertical());
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(fontRenderer.FONT_HEIGHT + this.getPadding().getHorizontal());
    }

}
