package com.github.franckyi.ibeeditor.gui.base;

import net.minecraft.client.gui.FontRenderer;

import java.util.function.Predicate;

public class GuiIntValueField extends GuiValueField<Integer> {

    private final double minVal, maxVal;

    public GuiIntValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width,
                            int par6Height) {
        this(componentId, fontrendererObj, x, y, par5Width, par6Height, 0);
    }

    public GuiIntValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height, int value) {
        this(componentId, fontrendererObj, x, y, par5Width, par6Height, value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public GuiIntValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height, int minVal, int maxVal) {
        this(componentId, fontrendererObj, x, y, par5Width, par6Height, 0, minVal, maxVal);
    }

    public GuiIntValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height, int value, int minVal, int maxVal) {
        super(componentId, fontrendererObj, x, y, par5Width, par6Height, value);
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    protected String fromValue(Integer value) {
        return value.toString();
    }

    @Override
    protected Integer toValue(String text) {
        try {
            return Integer.parseInt(text.isEmpty() ? "0" : text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    protected Predicate<Integer> validate() {
        return d -> (d >= minVal && d <= maxVal) || d == 0;
    }

}
