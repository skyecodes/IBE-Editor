package com.github.franckyi.gameadapter.forge.common.world;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.gameadapter.forge.common.nbt.ForgeCompoundTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ForgeItem implements Item {
    private final ItemStack item;
    private final CompoundTag data;

    public ForgeItem(ItemStack item) {
        this(item, new ForgeCompoundTag(item.save(new CompoundNBT())));
    }

    public ForgeItem(CompoundTag data) {
        this(ItemStack.of(data.get()), data);
    }

    public ForgeItem(ItemStack item, CompoundTag data) {
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
