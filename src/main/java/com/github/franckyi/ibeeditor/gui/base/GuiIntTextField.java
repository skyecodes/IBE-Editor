package com.github.franckyi.ibeeditor.gui.base;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class GuiIntTextField extends GuiTextField {

	public GuiIntTextField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
		super(componentId, fontrendererObj, x, y, par5Width, par6Height);
	}

	@Override
	public boolean textboxKeyTyped(char typedChar, int keyCode) {
        return (Character.isDigit(typedChar) || keyCode == 14 || keyCode == 203 || keyCode == 205 || keyCode == 211) && super.textboxKeyTyped(typedChar, keyCode);
    }

	public int getInt() {
		return Integer.parseInt(getText());
	}

}
