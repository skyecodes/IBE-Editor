package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public class PlayerMainHandItemUpdatePacket implements Packet {
    private final ItemStack itemStack;

    public PlayerMainHandItemUpdatePacket(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public PlayerMainHandItemUpdatePacket(FriendlyByteBuf buffer) {
        this(ItemStack.of(buffer.readNbt()));
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeNbt(itemStack.save(new CompoundTag()));
    }

    public ItemStack getItem() {
        return itemStack;
    }
}
