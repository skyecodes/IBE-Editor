package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyDouble;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyFloat;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

public class LootChancesCategory extends AbstractCategory {

    private final LivingEntity entity;

    public LootChancesCategory(MobEntity entity) {
        this.entity = entity;
        CompoundNBT tag = new CompoundNBT();
        entity.writeAdditional(tag);
        ListNBT armorDropChances = tag.getList("ArmorDropChances", Constants.NBT.TAG_FLOAT);
        ListNBT handDropChances = tag.getList("HandDropChances", Constants.NBT.TAG_FLOAT);
        Map<EquipmentSlotType, Float> dropChances = new TreeMap<>();
        for (EquipmentSlotType type : EquipmentSlotType.values()) {
            switch (type.getSlotType()) {
                case HAND:
                    dropChances.put(type, handDropChances.getFloat(type.getIndex()));
                    break;
                case ARMOR:
                    dropChances.put(type, armorDropChances.getFloat(type.getIndex()));
                    break;
            }
        }
        dropChances.forEach((slot, val) -> {
            PropertyFloat p = new PropertyFloat(StringUtils.capitalize(slot.getName()), val,
                    newVal -> entity.setDropChance(slot, newVal), 0.0f, 1.0f);
            p.getNameLabel().setPrefWidth(70);
            this.getChildren().add(p);
        });
    }
}
