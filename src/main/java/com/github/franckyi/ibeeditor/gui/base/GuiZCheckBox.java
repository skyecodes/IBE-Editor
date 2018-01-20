package com.github.franckyi.ibeeditor.gui.base;

import net.minecraftforge.fml.client.config.GuiCheckBox;

public class GuiZCheckBox extends GuiCheckBox {

	public GuiZCheckBox(int id, int xPos, int yPos, String displayString, boolean isChecked) {
		super(id, xPos, yPos, displayString, isChecked);
	}

	public GuiZCheckBox(int id, int xPos, int yPos, String displayString, boolean isChecked, float zLevel) {
		super(id, xPos, yPos, displayString, isChecked);
		this.setZLevel(zLevel);
	}

	public float getZLevel() {
		return zLevel;
	}

	public void setZLevel(float zLevel) {
		this.zLevel = zLevel;
	}

}
