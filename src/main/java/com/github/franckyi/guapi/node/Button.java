package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.IGuiView;
import com.google.common.collect.Lists;
import net.minecraftforge.fml.client.config.GuiButtonExt;

import java.util.List;

public class Button extends Node<Button.GuiButtonView> {

    protected Button(GuiButtonView view) {
        super(view);
        this.computeSize();
        this.updateSize();
    }

    public Button(String text) {
        this(text, new String[0]);
    }

    public Button(String text, String... tooltip) {
        this(new GuiButtonView(text, tooltip));
    }

    public Button() {
        this("Button");
    }

    public String getText() {
        return this.getView().displayString;
    }

    public void setText(String text) {
        this.getView().displayString = text;
        this.computeWidth();
        this.updateWidth();
    }

    public boolean isDisabled() {
        return !this.getView().enabled;
    }

    public void setDisabled(boolean disabled) {
        this.getView().enabled = !disabled;
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(mc.fontRenderer.getStringWidth(this.getText()) + this.getPadding().getVertical() + 10);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(20 + this.getPadding().getHorizontal());
    }

    public static class GuiButtonView extends GuiButtonExt implements IGuiView {

        protected final List<String> tooltipText;

        public GuiButtonView(String text, String[] tooltip) {
            super(0, 0, 0, text);
            this.tooltipText = Lists.newArrayList(tooltip);
        }

        @Override
        public int getViewX() {
            return this.x;
        }

        @Override
        public void setViewX(int x) {
            this.x = x;
        }

        @Override
        public int getViewY() {
            return this.y;
        }

        @Override
        public void setViewY(int y) {
            this.y = y;
        }

        @Override
        public int getViewWidth() {
            return super.getWidth();
        }

        @Override
        public void setViewWidth(int width) {
            super.setWidth(width);
        }

        @Override
        public int getViewHeight() {
            return this.height;
        }

        @Override
        public void setViewHeight(int height) {
            this.height = height;
        }

        @Override
        public boolean isViewVisible() {
            return visible;
        }

        @Override
        public void setViewVisible(boolean visible) {
            this.visible = visible;
        }

        @Override
        public void renderView(int mouseX, int mouseY, float partialTicks) {
            this.render(mouseX, mouseY, partialTicks);
            if (this.hovered && !tooltipText.isEmpty()) {
                mc.currentScreen.drawHoveringText(tooltipText, mouseX, mouseY);
            }
        }
    }
}
