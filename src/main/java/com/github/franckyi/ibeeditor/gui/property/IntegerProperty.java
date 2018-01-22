package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.base.GuiIntTextField;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class IntegerProperty extends BaseProperty<Integer> {

    private GuiIntTextField textField;

    public IntegerProperty(String name, Supplier<Integer> value, Consumer<Integer> apply) {
        super(name, value, apply);
        textField = new GuiIntTextField(0, mc.fontRenderer, 0, 0, 100, 14);
        textField.setText(getValue().toString());
    }

    @Override
    protected void place() {
        textField.x = slotRight - 110;
        textField.y = slotTop + 3;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        textField.textboxKeyTyped(typedChar, keyCode);
        setValue(textField.getValue());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        textField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        mc.fontRenderer.drawString(getName(), x + 5, slotTop + 6, 0xffffff);
        textField.drawTextBox();
    }

    @Override
    public void updateScreen() {
        textField.updateCursorCounter();
    }
}
