package com.github.franckyi.ibeeditor.gui.property.base;

import com.github.franckyi.ibeeditor.gui.base.GuiDoubleValueField;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DoubleProperty extends BaseProperty<Double> {

    private final GuiDoubleValueField textField;

    public DoubleProperty(String name, Supplier<Double> value, Consumer<Double> apply) {
        super(name, value, apply);
        textField = new GuiDoubleValueField(0, mc.fontRenderer, 0, 0, 100, 14);
        getTextfieldList().add(textField);
        init();
    }

    public DoubleProperty(String name, Supplier<Double> value, Consumer<Double> apply, double minVal, double maxVal) {
        super(name, value, apply);
        textField = new GuiDoubleValueField(0, mc.fontRenderer, 0, 0, 100, 14, minVal, maxVal);
        getTextfieldList().add(textField);
        init();
    }

    @Override
    protected void init() {
        super.init();
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