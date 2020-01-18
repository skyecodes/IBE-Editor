package com.github.franckyi.ibeeditor.client.logic.clipboard;

import com.github.franckyi.ibeeditor.client.ClientUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

public class EntityClipboardEntry implements IClipboardEntry {

    private final CompoundNBT entityTag;
    private EntityType<?> entityType;
    private Entity entity;

    public EntityClipboardEntry(Entity entity) {
        entityTag = ClientUtils.getCleanEntityTag(entity);
    }

    public EntityClipboardEntry(PacketBuffer buffer) {
        entityTag = buffer.readCompoundTag();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeCompoundTag(entityTag);
    }

    public CompoundNBT getEntityTag() {
        return entityTag;
    }

    public EntityType<?> getEntityType() {
        if (entityType == null) {
            entityType = EntityType.byKey(entityTag.getString("id")).orElse(EntityType.PIG);
        }
        return entityType;
    }

    public Entity getEntity() {
        if (entity == null) {
            entity = ClientUtils.createEntity(this.getEntityType(), entityTag);
        }
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityClipboardEntry entry = (EntityClipboardEntry) o;

        return entityTag.equals(entry.entityTag);

    }

    @Override
    public int hashCode() {
        return entityTag.hashCode();
    }
}
