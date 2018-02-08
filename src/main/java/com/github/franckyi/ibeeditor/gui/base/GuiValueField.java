package com.github.franckyi.ibeeditor.gui.base;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

import java.util.function.Predicate;

public abstract class GuiValueField<T> extends GuiTextField {

    private T value;

    public GuiValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
        this(componentId, fontrendererObj, x, y, par5Width, par6Height, null);
    }

    public GuiValueField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height, T value) {
        super(componentId, fontrendererObj, x, y, par5Width, par6Height);
        setValue(value);
        setValidator((s) -> validate().test(toValue(s)));
    }

    public T getValue() {
        return toValue(getText());
    }

    public void setValue(T value) {
        this.value = value;
        setText(fromValue(value));
    }

    protected abstract String fromValue(T value);

    protected abstract T toValue(String text);

    protected abstract Predicate<T> validate();
}
