package com.github.franckyi.ibeeditor.gui.base;

import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiEnumButton<V> extends GuiZButton {

	private List<IEnumButtonField<V>> fields = new ArrayList<>();
	private int i = 0;

	public GuiEnumButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText,
			Class<IEnumButtonField<V>> class1) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
        fields.addAll(Arrays.asList(class1.getEnumConstants()));
		displayString = fields.get(0).getButtonText();
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		if (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width
				&& mouseY < this.y + this.height && this.enabled) {
			i = (i != fields.size() - 1) ? i + 1 : 0;
			displayString = fields.get(i).getButtonText();
		}
		return super.mousePressed(mc, mouseX, mouseY);
	}

	public V getValue() {
		return fields.get(i).getButtonValue();
	}

	public void setValue(V value) {
		for (int i = 0; i < fields.size(); i++)
			if (fields.get(i).getButtonValue().equals(value)) {
				this.i = i;
				displayString = fields.get(i).getButtonText();
			}

	}

}
