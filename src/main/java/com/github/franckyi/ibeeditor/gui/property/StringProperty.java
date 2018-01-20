package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.base.GuiZTextField;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;

public class StringProperty extends BaseProperty<String> {

    private GuiZTextField textField;

    public StringProperty(String name, String value) {
        super(name, value);
        textField = new GuiZTextField(0, mc.fontRenderer, 0, 0, 100, 20);
        textField.setText(value);
    }

    @Override
    public void init() {
        textField.x = slotTop + 5;
        textField.y = slotRight - 110;
        textField.setZLevel(Float.MAX_VALUE);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        textField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        textField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        mc.fontRenderer.drawString(getName(), x, slotTop, 0xffffff);
        textField.drawTextBox();
        textField.updateCursorCounter();
    }
}
