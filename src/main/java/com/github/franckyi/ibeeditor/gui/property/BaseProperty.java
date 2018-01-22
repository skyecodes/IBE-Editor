package com.github.franckyi.ibeeditor.gui.property;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class BaseProperty<V> implements GuiListExtended.IGuiListEntry {

    protected static final Minecraft mc = Minecraft.getMinecraft();

    private String name;
    private V value;
    private final Supplier<V> defaultValue;
    private final Consumer<V> apply;

    protected int slotTop, slotBottom, slotLeft, slotRight;

    public BaseProperty(String name, Supplier<V> value, Consumer<V> apply) {
        setName(name);
        this.defaultValue = value;
        this.apply = apply;
        reset();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void place0(int slotTop, int slotBottom, int slotLeft, int slotRight) {
        this.slotTop = slotTop + 5;
        this.slotBottom = slotBottom;
        this.slotLeft = slotLeft;
        this.slotRight = slotRight;
        place();
    }

    public void apply() {
        apply.accept(value);
    }

    public void reset() {
        setValue(defaultValue.get());
    }

    protected abstract void place();

    public abstract void keyTyped(char typedChar, int keyCode);

    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);

    public abstract void updateScreen();

    @Override
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
    }

    @Override
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
        return true;
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
    }
}
