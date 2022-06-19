package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntityEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.SpawnEggItem;

public class ItemSpawnEggCategoryModel extends ItemEditorCategoryModel {
    private final SpawnEggItem item;
    private CompoundTag spawnData;

    public ItemSpawnEggCategoryModel(ItemEditorModel editor, SpawnEggItem item) {
        super(ModTexts.SPAWN_EGG, editor);
        this.item = item;
        spawnData = getTag().getCompound("EntityTag");
    }

    @Override
    protected void setupEntries() {
        getEntries().add(
                new EntityEntryModel(this, EntityType.by(spawnData).orElse(item.getType(getTag())), spawnData, this::setEntity)
        );
    }

    private void setEntity(CompoundTag compoundTag) {
        this.spawnData = compoundTag;
    }
}
