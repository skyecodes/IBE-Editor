package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.base.GuiEnumButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EnumProperty<E> extends BaseProperty<E> {

    private GuiEnumButton<E> button;
    private final List<E> values;
    private int index;

    public EnumProperty(String name, Collection<E> values, Supplier<E> value, Consumer<E> apply) {
        super(name, value, apply);
        this.values = new ArrayList<>(values);
        this.index = this.values.indexOf(getValue());
        this.button = new GuiEnumButton<>(0, 0, 0, 100, 20, "", this.values);
    }

    @Override
    protected void place() {
        button.x = slotRight - 110;
        button.y = slotTop;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        button.mousePressed(mc, mouseX, mouseY);
        setValue(button.getValue());
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        mc.fontRenderer.drawString(getName(), x + 5, slotTop + 6, 0xffffff);
        button.drawButton(mc, mouseX, mouseY);
    }

    @Override
    public void updateScreen() {

    }
}
