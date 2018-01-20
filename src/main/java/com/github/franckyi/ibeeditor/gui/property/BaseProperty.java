package com.github.franckyi.ibeeditor.gui.property;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

public abstract class BaseProperty<V> implements GuiListExtended.IGuiListEntry {

    protected static final Minecraft mc = Minecraft.getMinecraft();

    private String name;
    private V value;
    protected int slotTop, slotBottom, slotLeft, slotRight;

    public BaseProperty(String name, V value) {
        this.name = name;
        this.value = value;
    }

    public BaseProperty(String name) {
        this.name = name;
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

    public void init0(int slotTop, int slotBottom, int slotLeft, int slotRight) {
        this.slotTop = slotTop + 5;
        this.slotBottom = slotBottom;
        this.slotLeft = slotLeft;
        this.slotRight = slotRight;
        init();
    }

    public abstract void keyTyped(char typedChar, int keyCode);

    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);

    protected abstract void init();

    @Override
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
    }

    @Override
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
        mouseClicked(mouseX, mouseY, mouseEvent);
        return true;
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
    }
}
