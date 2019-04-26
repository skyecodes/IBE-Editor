package com.github.franckyi.ibeeditor.client.editor.entity;

import com.github.franckyi.ibeeditor.client.editor.Category;
import com.github.franckyi.ibeeditor.client.editor.property.custom.SlotProperty;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class InventoryEntityCategory extends Category {

    private final EntityLiving entity;
    private final Map<EntityEquipmentSlot, ItemStack> inventory;

    public InventoryEntityCategory(EntityLiving entity) {
        this.entity = entity;
        inventory = new HashMap<>();
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            inventory.put(slot, entity.getItemStackFromSlot(slot).copy());
        }
        build();
    }

    private void build() {
        inventory.forEach((slot, itemStack) -> this.addAll(new SlotProperty(StringUtils.capitalize(slot.getName()), itemStack, is -> this.update(slot, is))));
    }

    @Override
    public void apply() {
        inventory.forEach(entity::setItemStackToSlot);
    }

    private void update(EntityEquipmentSlot slot, ItemStack itemStack) {
        inventory.put(slot, itemStack);
        this.getChildren().clear();
        build();
        this.getChildren().forEach(p -> p.updateSize(this.getWidth()));
    }

}
