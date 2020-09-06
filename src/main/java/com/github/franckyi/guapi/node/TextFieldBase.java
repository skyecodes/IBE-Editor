package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.IValueNode;
import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.gui.IGuiView;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class TextFieldBase<T> extends Node<TextFieldBase.GuiTextFieldView> implements IValueNode<T> {

    private final Set<BiConsumer<T, T>> onValueChangedListeners;
    private T oldVal;

    public TextFieldBase() {
        super(new GuiTextFieldView());
        onValueChangedListeners = new HashSet<>();
        this.computeSize();
        this.updateSize();
    }

    @Override
    public int getWidth() {
        return super.getWidth() + 2;
    }

    @Override
    public int getX() {
        return super.getX() - 1;
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(100 + this.getPadding().getHorizontal());
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(16 + this.getPadding().getVertical());
    }

    public void tick() {
        this.getView().tick();
        if (this.getView().changed) {
            this.onValueChanged(oldVal, this.getValue());
        }
        this.getView().changed = false;
        oldVal = this.getValue();
    }

    @Override
    public Set<BiConsumer<T, T>> getOnValueChangedListeners() {
        return onValueChangedListeners;
    }

    public abstract T getValue();

    public abstract void setValue(T value);

    protected final String getText() {
        return this.getView().getText();
    }

    protected final void setText(String text) {
        this.getView().setText(text);
    }

    public List<String> getTooltipText() {
        return this.getView().tooltipText;
    }

    public static class GuiTextFieldView extends TextFieldWidget implements IGuiView {

        private final List<String> tooltipText;
        private Scene.GUAPIScreen screen;
        private boolean changed, flag;

        public GuiTextFieldView(String... tooltipText) {
            super(mc.fontRenderer, 0, 0, 0, 0, ITextComponent.func_244388_a(""));
            this.tooltipText = Lists.newArrayList(tooltipText);
            this.setMaxStringLength(Short.MAX_VALUE);
        }

        @Override
        public int getViewX() {
            return x;
        }

        @Override
        public void setViewX(int x) {
            this.x = x;
        }

        @Override
        public int getViewY() {
            return y;
        }

        @Override
        public void setViewY(int y) {
            this.y = y;
        }

        @Override
        public int getViewWidth() {
            return this.getWidth();
        }

        @Override
        public void setViewWidth(int width) {
            this.setWidth(width);
        }

        @Override
        public int getViewHeight() {
            return this.height;
        }

        @Override
        public void setViewHeight(int height) {
            this.setHeight(height);
        }

        @Override
        public boolean isViewVisible() {
            return this.getVisible();
        }

        @Override
        public void setViewVisible(boolean visible) {
            this.setVisible(visible);
        }

        @Override
        public void renderView(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            if (!flag) {
                flag = true;
                this.setCursorPosition(0);
            }
            this.render(matrixStack, mouseX, mouseY, partialTicks);
            if (this.inBounds(mouseX, mouseY)) {
                if (screen == null) {
                    screen = (Scene.GUAPIScreen) mc.currentScreen;
                }
                screen.renderTooltip(tooltipText, mouseX, mouseY);
            }
        }

        @Override
        public boolean inBounds(double x, double y) {
            return x >= this.getViewX() && x <= this.getViewX() + this.getViewWidth() &&
                    y >= this.getViewY() && y <= this.getViewY() + this.getViewHeight();
        }

        @Override
        public void setText(String textIn) {
            String old = this.getText();
            super.setText(textIn);
            changed = !this.getText().equals(old);
        }

        @Override
        public void writeText(String textToWrite) {
            String old = this.getText();
            super.writeText(textToWrite);
            changed = !this.getText().equals(old);
        }

        @Override
        public void deleteFromCursor(int num) {
            String old = this.getText();
            super.deleteFromCursor(num);
            changed = !this.getText().equals(old);
        }

        @Override
        public void setMaxStringLength(int length) {
            String old = this.getText();
            super.setMaxStringLength(length);
            changed = !this.getText().equals(old);
        }
    }
}
