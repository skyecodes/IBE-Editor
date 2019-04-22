package com.github.franckyi.ibeeditor.client.clipboard.logic;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class EntityClipboardEntry implements IClipboardEntry {

    private final NBTTagCompound entityTag;

    public EntityClipboardEntry(NBTTagCompound entityTag) {
        this.entityTag = entityTag;
    }

    public EntityClipboardEntry(PacketBuffer buffer) {
        entityTag = buffer.readCompoundTag();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeCompoundTag(entityTag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityClipboardEntry that = (EntityClipboardEntry) o;

        return entityTag.equals(that.entityTag);

    }

    @Override
    public int hashCode() {
        return entityTag.hashCode();
    }
}
