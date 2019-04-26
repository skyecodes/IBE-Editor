package com.github.franckyi.ibeeditor.client.clipboard.logic;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

import java.util.Objects;

public class EntityClipboardEntry implements IClipboardEntry {

    private final EntityType<?> entityType;
    private final NBTTagCompound entityTag;
    private Entity entity;

    public EntityClipboardEntry(Entity entity) {
        entityType = entity.getType();
        entityTag = new NBTTagCompound();
        entity.writeUnlessRemoved(entityTag);
    }

    public EntityClipboardEntry(PacketBuffer buffer) {
        entityType = EntityType.getById(buffer.readResourceLocation().toString());
        entityTag = buffer.readCompoundTag();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeResourceLocation(entityType.getRegistryName());
        buffer.writeCompoundTag(entityTag);
    }

    public EntityType<?> getEntityType() {
        return entityType;
    }

    public NBTTagCompound getEntityTag() {
        return entityTag;
    }

    public Entity getEntity() {
        if (entity == null) {
            entity = entityType.create(Minecraft.getInstance().world);
            entity.read(entityTag);
        }
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityClipboardEntry that = (EntityClipboardEntry) o;
        return entityType.equals(that.entityType) &&
                entityTag.equals(that.entityTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityType, entityTag);
    }
}
