package com.github.franckyi.ibeeditor.gui.base;

import net.minecraft.client.gui.FontRenderer;

import java.util.function.Predicate;

public class GuiDoubleValueField extends GuiValueField<Double> {

    private final double minVal, maxVal;

    public GuiDoubleValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width,
                               int par6Height) {
        this(componentId, fontrendererObj, x, y, par5Width, par6Height, 0.);
    }

    public GuiDoubleValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height, double value) {
        this(componentId, fontrendererObj, x, y, par5Width, par6Height, value, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public GuiDoubleValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height, double minVal, double maxVal) {
        this(componentId, fontrendererObj, x, y, par5Width, par6Height, 0., minVal, maxVal);
    }

    public GuiDoubleValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height, double value, double minVal, double maxVal) {
        super(componentId, fontrendererObj, x, y, par5Width, par6Height, value);
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    protected String fromValue(Double value) {
        return value.toString();
    }

    @Override
    protected Double toValue(String text) {
        try {
            return Double.parseDouble(text.isEmpty() ? "0" : text);
        } catch (NumberFormatException e) {
            return 0.;
        }
    }

    @Override
    protected Predicate<Double> validate() {
        return d -> (d >= minVal && d <= maxVal) || d == 0;
    }

}
