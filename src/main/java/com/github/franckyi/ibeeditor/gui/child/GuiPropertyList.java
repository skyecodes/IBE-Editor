package com.github.franckyi.ibeeditor.gui.child;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

import javax.annotation.Nonnull;
import java.util.List;

public class GuiPropertyList<T extends BaseProperty<?>> extends GuiListExtended {

    protected final GuiEditor parent;
    protected final Minecraft mc;
    protected List<T> properties;

    public GuiPropertyList(GuiEditor parent, Minecraft mc, int slotHeight, List<T> properties) {
        super(mc, 2 * parent.width / 3 - 20, parent.height - 60, 20, parent.height - 40, slotHeight);
        this.parent = parent;
        this.mc = mc;
        this.properties = properties;
        this.left = parent.width / 3 + 10;
        this.right = left + parent.width;
    }

    protected void place() {
        int amountScrolled = Math.min(getMaxScroll(), Math.max(0, getAmountScrolled()));
        for (int i = 0; i < properties.size(); i++) {
            properties.get(i).place0(top + headerPadding + i * slotHeight - amountScrolled, top + headerPadding + (i + 1) * slotHeight - amountScrolled, left, right);
        }
    }

    @Override
    public @Nonnull
    IGuiListEntry getListEntry(int index) {
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
        if (posX >= left && posX <= right && posY >= top + headerPadding && posY <= bottom) {
            int slot = (posY - top - headerPadding) / slotHeight;
            if (slot >= 0 && slot < properties.size()) {
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
