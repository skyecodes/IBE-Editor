package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.impl.common.nbt.ForgeCompoundTag;
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
