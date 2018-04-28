package com.github.franckyi.ibeeditor.gui.property.common;

import com.github.franckyi.ibeeditor.gui.GuiBlockEditor;
import com.github.franckyi.ibeeditor.gui.GuiItemEditor;
import com.github.franckyi.ibeeditor.gui.property.base.BaseProperty;
import com.github.franckyi.ibeeditor.util.item.TileEntityInventoryItemHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class EditItemProperty extends BaseProperty<Void> {

    private GuiBlockEditor parent;
    private ItemStack itemStack;
    private int slotId;
    private BlockPos blockPos;

    private GuiButton button;

    public EditItemProperty(GuiBlockEditor parent, ItemStack itemStack, int slotId, BlockPos blockPos) {
        super(String.format("%s (Slot %d)", itemStack.getDisplayName(), slotId), () -> null);
        this.parent = parent;
        this.itemStack = itemStack;
        this.slotId = slotId;
        this.blockPos = blockPos;
        this.button = new GuiButton(0, 0, 0, 60, 20, "Edit >");
        getButtonList().add(button);
        undo.visible = false;
        init();
    }

    @Override
    protected void place() {
        super.place();
        button.x = slotRight - 70;
        button.y = slotTop;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (button.mousePressed(mc, mouseX, mouseY)) {
            mc.displayGuiScreen(new GuiItemEditor(parent, new TileEntityInventoryItemHandler(itemStack, slotId, blockPos)));
        }
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
        super.drawEntry(slotIndex, x, y, listWidth, slotHeight, mouseX, mouseY, isSelected, partialTicks);
        mc.fontRenderer.drawString(getName(), x + 5, slotTop + 6, 0xffffff);
    }
}