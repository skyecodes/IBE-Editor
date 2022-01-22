package com.github.franckyi.ibeeditor.client.screen.model.entry.vault;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableObjectValue;
import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.client.screen.model.category.vault.VaultEntityCategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class VaultEntityEntryModel extends EntryModel {
    private final ObjectProperty<CompoundTag> tagProperty;
    private final ObservableObjectValue<Entity> entityProperty;

    public VaultEntityEntryModel(VaultEntityCategoryModel parent, CompoundTag tag) {
        super(parent);
        tagProperty = ObjectProperty.create(tag);
        entityProperty = tagProperty.map(tag1 -> EntityType.create(tag1, Minecraft.getInstance().level).orElse(null));
    }

    public CompoundTag getData() {
        return tagProperty().getValue();
    }

    public ObjectProperty<CompoundTag> tagProperty() {
        return tagProperty;
    }

    public void setData(CompoundTag value) {
        tagProperty().setValue(value);
    }

    public Entity getEntity() {
        return entityProperty().getValue();
    }

    public ObservableObjectValue<Entity> entityProperty() {
        return entityProperty;
    }

    @Override
    public void apply() {
        Vault.getInstance().saveEntity(getData());
    }

    @Override
    public Type getType() {
        return Type.VAULT_ENTITY;
    }
}
