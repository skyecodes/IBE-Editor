package com.github.franckyi.ibeeditor.client.gui.editor.base.category.capability;

import com.github.franckyi.ibeeditor.client.gui.editor.base.category.CapabilityCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.SlotProperty;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.HashMap;
import java.util.Map;

public class ItemHandlerCategory extends CapabilityCategory<IItemHandler> {

    private final Map<Integer, ItemStack> inventory;

    public ItemHandlerCategory(IItemHandler handler) {
        super(handler);
        inventory = new HashMap<>();
        for (int i = 0; i < handler.getSlots(); i++) {
            inventory.put(i, handler.getStackInSlot(i).copy());
        }
        build();
    }

    private void build() {
        inventory.forEach((slot, itemStack) -> this.addAll(new SlotProperty("Slot #" + slot, itemStack, is -> this.update(slot, is))));
    }

    @Override
    public void apply() {
        inventory.forEach((slot, itemStack) -> {
            capability.extractItem(slot, Integer.MAX_VALUE, false);
            capability.insertItem(slot, itemStack, false);
        });
    }

    private void update(int slot, ItemStack itemStack) {
        inventory.put(slot, itemStack);
        this.getChildren().clear();
        build();
        this.getChildren().forEach(p -> p.updateSize(this.getWidth()));
    }
}
