package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.IGuiView;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return this.getView().getMessage().getString();
    }

    public void setText(String text) {
        this.getView().setMessage(ITextComponent.getTextComponentOrEmpty(text));
        this.computeWidth();
        this.updateWidth();
    }

    public boolean isDisabled() {
        return !this.getView().active;
    }

    public void setDisabled(boolean disabled) {
        this.getView().active = !disabled;
    }

    public List<ITextComponent> getTooltipText() {
        return this.getView().tooltipText;
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(mc.fontRenderer.getStringWidth(this.getText()) + this.getPadding().getVertical() + 10);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(20 + this.getPadding().getHorizontal());
    }

    public static class GuiButtonView extends net.minecraft.client.gui.widget.button.Button implements IGuiView {

        protected final List<ITextComponent> tooltipText;

        public GuiButtonView(String text, String[] tooltip) {
            super(0, 0, 0, 0, ITextComponent.getTextComponentOrEmpty(text), (b) -> {
            });
            this.tooltipText = Stream.of(tooltip).map(ITextComponent::getTextComponentOrEmpty).collect(Collectors.toList());
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
        public void renderView(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            this.render(matrixStack, mouseX, mouseY, partialTicks);
            if (this.isHovered() && !tooltipText.isEmpty()) {
                mc.currentScreen.func_243308_b(matrixStack, tooltipText, mouseX, mouseY);
            }
        }
    }
}
