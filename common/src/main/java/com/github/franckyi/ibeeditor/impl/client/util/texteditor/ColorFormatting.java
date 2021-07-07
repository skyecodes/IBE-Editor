package com.github.franckyi.ibeeditor.impl.client.util.texteditor;

import com.github.franckyi.minecraft.api.common.text.Text;

public class ColorFormatting extends Formatting {
    private String color;

    public ColorFormatting(int start, int end, String color) {
        super(start, end);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void apply(Text text) {
        text.setColor(color);
    }
}
