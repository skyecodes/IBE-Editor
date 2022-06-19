package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;

import java.util.function.Consumer;

public class EntityEntryModel extends ValueEntryModel<CompoundTag> {
    private EntityType<?> entityType;

    public EntityEntryModel(CategoryModel category, EntityType<?> entityType, CompoundTag spawnData, Consumer<CompoundTag> action) {
        super(category, ModTexts.ENTITY, spawnData, action);
        this.entityType = entityType;
    }

    @Override
    public void apply() {

    }

    @Override
    public Type getType() {
        return Type.ENTITY;
    }
}
