package com.github.franckyi.gameadapter.fabric.common.world;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.gameadapter.fabric.common.nbt.FabricCompoundTag;
import net.minecraft.nbt.NbtCompound;

public class FabricItem implements Item {
    private final net.minecraft.item.ItemStack item;
    private final CompoundTag data;

    public FabricItem(net.minecraft.item.ItemStack item) {
        this(item, new FabricCompoundTag(item.writeNbt(new NbtCompound())));
    }

    public FabricItem(CompoundTag data) {
        this(net.minecraft.item.ItemStack.fromNbt(data.get()), data);
    }

    private FabricItem(net.minecraft.item.ItemStack item, CompoundTag data) {
        this.item = item;
        this.data = data;
    }

    @Override
    public CompoundTag getData() {
        return data;
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.item.ItemStack get() {
        return item;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
