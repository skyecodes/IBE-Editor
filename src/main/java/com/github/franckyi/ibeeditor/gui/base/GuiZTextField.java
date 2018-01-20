package com.github.franckyi.ibeeditor.gui.base;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class GuiZTextField extends GuiTextField {

    public GuiZTextField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
        super(componentId, fontrendererObj, x, y, par5Width, par6Height);
    }

    public float getZLevel() {
        return zLevel;
    }

    public void setZLevel(float zLevel) {
        this.zLevel = zLevel;
    }
}
