package com.github.franckyi.ibeeditor.gui.base;

import net.minecraft.client.gui.GuiButton;

public class GuiZButton extends GuiButton {

	public GuiZButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}

	public GuiZButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, float zLevel) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.setZLevel(zLevel);
	}

	public float getZLevel() {
		return zLevel;
	}

	public void setZLevel(float zLevel) {
		this.zLevel = zLevel;
	}

}
