package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.SlotProperty;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

public class InventoryEntityCategory extends AbstractCategory {

    private final LivingEntity entity;
    private final Map<EquipmentSlotType, ItemStack> inventory;

    public InventoryEntityCategory(LivingEntity entity) {
        this.entity = entity;
        inventory = new TreeMap<>();
        for (EquipmentSlotType slot : EquipmentSlotType.values()) {
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

    private void update(EquipmentSlotType slot, ItemStack itemStack) {
        inventory.put(slot, itemStack);
        this.getChildren().clear();
        build();
        this.getChildren().forEach(p -> p.updateSize(this.getWidth()));
    }

}
