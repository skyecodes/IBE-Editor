package com.github.franckyi.ibeeditor.gui.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.util.List;

public class GuiEnumButton<V> extends GuiButton {

    private final List<V> values;
    private int index = 0;

    public GuiEnumButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText,
                         List<V> values) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.values = values;
        updateDisplayString();
    }

    private void updateDisplayString() {
        displayString = getValue().toString();
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            if (++index == values.size()) index = 0;
            updateDisplayString();
            return true;
        }
        return false;
    }

    public V getValue() {
        return values.get(index);
    }

    public void setValue(V value) {
        int index = values.indexOf(value);
        if (index != -1) {
            this.index = index;
            updateDisplayString();
        }
    }

}
