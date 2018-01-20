package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

import java.util.ArrayList;
import java.util.List;

public class GuiPropertyList extends GuiListExtended {

    protected final Minecraft mc;
    private List<BaseProperty<?>> properties = new ArrayList<>();

    public GuiPropertyList(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, 30);
        this.mc = mcIn;
    }

    public void setProperties(List<BaseProperty<?>> properties) {
        this.properties = properties;
        for (int i = 0; i < properties.size(); i++) {
            properties.get(i).init0(top + i * slotHeight, top + (i+1) * slotHeight, left, right);
        }
    }

    @Override
    public IGuiListEntry getListEntry(int index) {
        return properties.get(index);
    }

    @Override
    public int getListWidth() {
        return width;
    }

    @Override
    protected int getSize() {
        return properties.size();
    }

    public void keyTyped(char typedChar, int keyCode) {
        properties.forEach(property -> property.keyTyped(typedChar, keyCode));
    }

}
