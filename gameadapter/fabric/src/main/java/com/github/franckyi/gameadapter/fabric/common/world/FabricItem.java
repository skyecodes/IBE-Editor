package com.github.franckyi.gameadapter.fabric.common.world;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.gameadapter.fabric.common.nbt.FabricCompoundTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class FabricItem implements Item {
    private final ItemStack item;
    private final CompoundTag data;

    public FabricItem(ItemStack item) {
        this(item, new FabricCompoundTag(item.writeNbt(new NbtCompound())));
    }

    public FabricItem(CompoundTag data) {
        this(ItemStack.fromNbt(data.get()), data);
    }

    private FabricItem(ItemStack item, CompoundTag data) {
        this.item = item;
        this.data = data;
    }

    @Override
    public CompoundTag getData() {
        return data;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ItemStack get() {
        return item;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
