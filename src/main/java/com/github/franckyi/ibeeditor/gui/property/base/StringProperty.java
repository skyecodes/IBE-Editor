package com.github.franckyi.ibeeditor.gui.property.base;

import net.minecraft.client.gui.GuiTextField;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class StringProperty extends BaseProperty<String> {

    protected final GuiTextField textField;

    public StringProperty(String name, Supplier<String> value, Consumer<String> apply) {
        super(name, value, apply);
        textField = new GuiTextField(0, mc.fontRenderer, 0, 0, 100, 14);
        getTextfieldList().add(textField);
        init();
    }

    @Override
    protected void init() {
        textField.setText(getValue());
    }

    @Override
    protected void place() {
        super.place();
        textField.x = slotRight - 140;
        textField.y = slotTop + 3;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        setValue(textField.getText());
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
        super.drawEntry(slotIndex, x, y, listWidth, slotHeight, mouseX, mouseY, isSelected, partialTicks);
        mc.fontRenderer.drawString(getName(), x + 5, slotTop + 6, 0xffffff);
    }

}