package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.IValueNode;
import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.IGuiView;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.HashSet;
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

    public int getWidth() {
        return super.getWidth() + 10;
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

    public static class GuiTextFieldView extends GuiTextField implements IGuiView {

        private boolean changed;

        public GuiTextFieldView() {
            super(0, mc.fontRenderer, 0, 0, 0, 0);
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
            return super.getWidth();
        }

        @Override
        public void setViewWidth(int width) {
            this.width = width;
            ObfuscationReflectionHelper.setPrivateValue(GuiTextField.class, this, 0, "field_146225_q");
        }

        @Override
        public int getViewHeight() {
            return height;
        }

        @Override
        public void setViewHeight(int height) {
            this.height = height;
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
        public void renderView(int mouseX, int mouseY, float partialTicks) {
            this.drawTextField(mouseX, mouseY, partialTicks);
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
