package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.base.GuiEnumButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EnumProperty<E> extends BaseProperty<E> {

    private final GuiEnumButton<E> button;

    public EnumProperty(String name, Collection<E> values, Supplier<E> value, Consumer<E> apply) {
        super(name, value, apply);
        this.button = new GuiEnumButton<>(0, 0, 0, 100, 20, "", new ArrayList<>(values));
        getButtonList().add(button);
        init();
    }

    @Override
    protected void init() {
        button.setValue(getValue());
    }

    @Override
    protected void place() {
        super.place();
        button.x = slotRight - 140;
        button.y = slotTop;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        setValue(button.getValue());
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
        super.drawEntry(slotIndex, x, y, listWidth, slotHeight, mouseX, mouseY, isSelected, partialTicks);
        mc.fontRenderer.drawString(getName(), x + 5, slotTop + 6, 0xffffff);
    }

}
