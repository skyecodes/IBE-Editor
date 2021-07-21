package com.github.franckyi.ibeeditor.impl.client.util.texteditor;

import com.github.franckyi.gameadapter.api.common.text.Text;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColorFormatting that = (ColorFormatting) o;
        return Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
