package com.github.franckyi.ibeeditor.gui.child;

import com.github.franckyi.ibeeditor.gui.GuiEditor;
import com.github.franckyi.ibeeditor.gui.property.PropertyCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class GuiCategoryList extends GuiListExtended {

    protected final GuiEditor parent;
    protected final Minecraft mc;
    protected final List<Entry> categories;

    public GuiCategoryList(GuiEditor parent, Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, List<PropertyCategory> categoryList, int currentCategory) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, 20);
        this.parent = parent;
        this.mc = mcIn;
        this.categories = categoryList.stream().map(Entry::new).collect(Collectors.toList());
        this.selectedElement = currentCategory;
    }

    @Override
    public @Nonnull IGuiListEntry getListEntry(int index) {
        return categories.get(index);
    }

    @Override
    public int getListWidth() {
        return width;
    }

    @Override
    protected int getSize() {
        return categories.size();
    }

    private class Entry implements IGuiListEntry {

        private final String categoryName;

        public Entry(PropertyCategory category) {
            this.categoryName = category.getCategoryName();
        }

        @Override
        public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {

        }

        @Override
        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
            mc.fontRenderer.drawString(categoryName + (selectedElement == slotIndex ? " >" : ""), x + 5, y + 2, 0xffffff);
        }

        @Override
        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
            parent.selectCategory(slotIndex);
            return true;
        }

        @Override
        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        }
    }

}
