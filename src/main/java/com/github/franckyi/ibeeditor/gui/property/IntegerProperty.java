package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.base.GuiIntValueField;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class IntegerProperty extends BaseProperty<Integer> {

    private final GuiIntValueField textField;

    public IntegerProperty(String name, Supplier<Integer> value, Consumer<Integer> apply) {
        super(name, value, apply);
        textField = new GuiIntValueField(0, mc.fontRenderer, 0, 0, 100, 14);
        getTextfieldList().add(textField);
        init();
    }

    public IntegerProperty(String name, Supplier<Integer> value, Consumer<Integer> apply, int minVal, int maxVal) {
        super(name, value, apply);
        textField = new GuiIntValueField(0, mc.fontRenderer, 0, 0, 100, 14, minVal, maxVal);
        getTextfieldList().add(textField);
        init();
    }

    @Override
    protected void init() {
        textField.setText(getValue().toString());
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
        setValue(textField.getValue());
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
        super.drawEntry(slotIndex, x, y, listWidth, slotHeight, mouseX, mouseY, isSelected, partialTicks);
        mc.fontRenderer.drawString(getName(), x + 5, slotTop + 6, 0xffffff);
    }

}
