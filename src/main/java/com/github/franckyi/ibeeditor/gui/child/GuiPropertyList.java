package com.github.franckyi.ibeeditor.gui.child;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

import javax.annotation.Nonnull;
import java.util.List;

public class GuiPropertyList extends GuiListExtended {

    protected final GuiEditor parent;
    protected final Minecraft mc;
    protected List<BaseProperty<?>> properties;

    public GuiPropertyList(GuiEditor parent, Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, List<BaseProperty<?>> properties) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, 25);
        this.parent = parent;
        this.mc = mcIn;
        this.properties = properties;
    }

    protected void place() {
        int amountScrolled = Math.min(getMaxScroll(), Math.max(0, getAmountScrolled()));
        for (int i = 0; i < properties.size(); i++) {
            properties.get(i).place0(top + i * slotHeight - amountScrolled, top + (i+1) * slotHeight - amountScrolled, left, right);
        }
    }

    @Override
    public @Nonnull IGuiListEntry getListEntry(int index) {
        return properties.get(index);
    }

    @Override
    public int getListWidth() {
        return width;
    }

    @Override
    protected int getScrollBarX() {
        return this.right - 6;
    }

    @Override
    public int getSlotIndexFromScreenCoords(int posX, int posY) {
        if(posX >= left && posX <= right && posY >= top && posY <= bottom) {
            int slot = (posY - top) / slotHeight;
            if(slot >= 0 && slot < properties.size()) {
                return slot;
            }
        }
        return -1;
    }

    @Override
    public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks) {
        place();
        super.drawScreen(mouseXIn, mouseYIn, partialTicks);
        this.overlayBackground(this.bottom, this.bottom + 60, 255, 255);
    }

    @Override
    protected int getSize() {
        return properties.size();
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseEvent) {
        properties.forEach(property -> property.mouseClicked(mouseX, mouseY, mouseEvent));
        return super.mouseClicked(mouseX, mouseY, mouseEvent);
    }

    public void keyTyped(char typedChar, int keyCode) {
        properties.forEach(property -> property.keyTyped(typedChar, keyCode));
    }

    public void updateScreen() {
        properties.forEach(BaseProperty::updateScreen);
    }
}
