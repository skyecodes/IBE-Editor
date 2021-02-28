package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.gamehooks.impl.common.tag.FabricCompoundTag;
import net.minecraft.item.ItemStack;

public class FabricItem implements Item {
    private final ItemStack item;
    private final CompoundTag tag;

    public FabricItem(ItemStack item) {
        this(item, new FabricCompoundTag(item.getTag()));
    }

    public FabricItem(CompoundTag tag) {
        this(ItemStack.fromTag(tag.get()), tag);
    }

    private FabricItem(ItemStack item, CompoundTag tag) {
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
