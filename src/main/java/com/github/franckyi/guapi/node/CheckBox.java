package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.IValueNode;
import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.IGuiView;
import net.minecraftforge.fml.client.config.GuiCheckBox;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class CheckBox extends Node<CheckBox.GuiCheckBoxView> implements IValueNode<Boolean> {

    private final Set<BiConsumer<Boolean, Boolean>> onValueChangedListeners;

    public CheckBox() {
        this("", false);
    }

    public CheckBox(String text) {
        this(text, false);
    }

    public CheckBox(boolean checked) {
        this("", checked);
    }

    public CheckBox(String text, boolean checked) {
        super(new GuiCheckBoxView(text, checked));
        onValueChangedListeners = new HashSet<>();
    }

    public String getText() {
        return this.getView().displayString;
    }

    public void setText(String text) {
        this.getView().displayString = text;
    }

    public boolean isChecked() {
        return this.getView().isChecked();
    }

    public void setChecked(boolean checked) {
        boolean old = this.isChecked();
        this.getView().setIsChecked(checked);
        if (old != checked) {
            this.onValueChanged(old, checked);
        }
    }

    public boolean isDisabled() {
        return !this.getView().enabled;
    }

    public void setDisabled(boolean disabled) {
        this.getView().enabled = !disabled;
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(11 + 2 + mc.fontRenderer.getStringWidth(this.getText()));
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(11);
    }

    @Override
    public Set<BiConsumer<Boolean, Boolean>> getOnValueChangedListeners() {
        return onValueChangedListeners;
    }

    public static class GuiCheckBoxView extends GuiCheckBox implements IGuiView {

        public GuiCheckBoxView(String displayString, boolean isChecked) {
            super(0, 0, 0, displayString, isChecked);
            packedFGColor = 0xffffff;
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
            super.setWidth(width);
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
            return visible;
        }

        @Override
        public void setViewVisible(boolean visible) {
            this.visible = visible;
        }

        @Override
        public void renderView(int mouseX, int mouseY, float partialTicks) {
            this.render(mouseX, mouseY, partialTicks);
        }
    }
}
