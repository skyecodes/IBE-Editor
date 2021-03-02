package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.gamehooks.impl.common.tag.ForgeCompoundTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ForgeItem implements Item {
    private final ItemStack item;
    private final CompoundTag tag;

    public ForgeItem(ItemStack item) {
        this(item, new ForgeCompoundTag(item.write(new CompoundNBT())));
    }

    public ForgeItem(CompoundTag tag) {
        this(ItemStack.read(tag.get()), tag);
    }

    public ForgeItem(ItemStack item, CompoundTag tag) {
        this.item = item;
        this.tag = tag;
    }

    @Override
    public CompoundTag getTag() {
        return tag;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ItemStack get() {
        return item;
    }
}
