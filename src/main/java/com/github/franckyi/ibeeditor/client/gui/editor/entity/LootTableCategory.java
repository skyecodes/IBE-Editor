package com.github.franckyi.ibeeditor.client.gui.editor.entity;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyInteger;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyString;
import net.minecraft.entity.MobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class LootTableCategory extends AbstractCategory {
    private final MobEntity entity;
    private CompoundNBT nbt;

    public LootTableCategory(MobEntity entity) {
        this.entity = entity;
        this.nbt = new CompoundNBT();
        entity.writeAdditional(nbt);
        this.getChildren().add(new PropertyString("Name", entity.getLootTableResourceLocation().toString(), this::setLootTableResourceLocation));
        this.getChildren().add(new PropertyInteger("Seed", (int) nbt.getLong("DeathLootTableSeed"), seed -> nbt.putLong("DeathLootTableSeed", seed)));
    }

    private void setLootTableResourceLocation(String s) {
        ResourceLocation resourceLocation = ResourceLocation.tryCreate(s);
        if (resourceLocation != null) {
            nbt.putString("DeathLootTable", resourceLocation.toString());
        }
    }

    @Override
    public void apply() {
        entity.writeAdditional(nbt);
        super.apply();
        entity.readAdditional(nbt);
    }
}
