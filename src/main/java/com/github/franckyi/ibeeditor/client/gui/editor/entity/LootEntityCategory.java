package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyDouble;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

public class LootEntityCategory extends AbstractCategory {

    private final LivingEntity entity;

    public LootEntityCategory(MobEntity entity) {
        this.entity = entity;
        CompoundNBT tag = new CompoundNBT();
        entity.writeAdditional(tag);
        ListNBT armorDropChances = tag.getList("ArmorDropChances", Constants.NBT.TAG_FLOAT);
        ListNBT handDropChances = tag.getList("HandDropChances", Constants.NBT.TAG_FLOAT);
        Map<EquipmentSlotType, Double> dropChances = new TreeMap<>();
        for (EquipmentSlotType type : EquipmentSlotType.values()) {
            switch (type.getSlotType()) {
                case HAND:
                    dropChances.put(type, (double) handDropChances.getFloat(type.getIndex()));
                    break;
                case ARMOR:
                    dropChances.put(type, (double) armorDropChances.getFloat(type.getIndex()));
                    break;
            }
        }
        dropChances.forEach((slot, val) -> {
            PropertyDouble p = new PropertyDouble(StringUtils.capitalize(slot.getName()), val,
                    newVal -> entity.setDropChance(slot, newVal.floatValue()), 0, 1);
            p.getNameLabel().setPrefWidth(70);
            this.getChildren().add(p);
        });
    }
}
