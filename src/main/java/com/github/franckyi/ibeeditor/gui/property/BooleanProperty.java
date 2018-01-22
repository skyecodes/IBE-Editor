package com.github.franckyi.ibeeditor.gui.property;

import net.minecraftforge.fml.client.config.GuiCheckBox;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BooleanProperty extends BaseProperty<Boolean> {

    private GuiCheckBox checkBox;

    public BooleanProperty(String name, Supplier<Boolean> value, Consumer<Boolean> apply) {
        super(name, value, apply);
        checkBox = new GuiCheckBox(0, 0, 0, name, getValue());
    }

    @Override
    protected void place() {
        checkBox.x = slotLeft + 5;
        checkBox.y = slotTop + 3;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        checkBox.mousePressed(mc, mouseX, mouseY);
        setValue(checkBox.isChecked());
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        checkBox.drawButton(mc, mouseX, mouseY);
    }

    @Override
    public void updateScreen() {

    }
}
