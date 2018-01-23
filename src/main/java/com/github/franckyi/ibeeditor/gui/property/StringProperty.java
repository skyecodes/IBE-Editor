package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.base.GuiFormatButton;
import net.minecraft.client.gui.GuiTextField;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class StringProperty extends BaseProperty<String> {

    private final GuiTextField textField;
    private final GuiFormatButton formatButton;

    public StringProperty(String name, Supplier<String> value, Consumer<String> apply) {
        super(name, value, apply);
        textField = new GuiTextField(0, mc.fontRenderer, 0, 0, 100, 14);
        formatButton = new GuiFormatButton(0, 0, 0, textField);
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
        formatButton.x = slotRight - 170;
        formatButton.y = slotTop;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        textField.textboxKeyTyped(typedChar, keyCode);
        setValue(textField.getText());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        textField.mouseClicked(mouseX, mouseY, mouseButton);
        formatButton.mousePressed(mc, mouseX, mouseY);
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        super.drawEntry(slotIndex, x, y, listWidth, slotHeight, mouseX, mouseY, isSelected);
        mc.fontRenderer.drawString(getName(), x + 5, slotTop + 6, 0xffffff);
        textField.drawTextBox();
        formatButton.drawButton(mc, mouseX, mouseY);
    }

    @Override
    public void updateScreen() {
        textField.updateCursorCounter();
    }
}
