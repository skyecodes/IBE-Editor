package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class FabricItem implements Item {
    private ItemStack item;
    private ObjectTag tag;

    public FabricItem(ItemStack item) {
        this.item = item;
    }

    public FabricItem(ObjectTag tag) {
        this.tag = tag;
    }

    @Override
    public ObjectTag getTag() {
        if (tag == null) {
            tag = FabricTagFactory.parseCompound(item.toTag(new CompoundTag()));
        }
        return tag;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ItemStack getStack() {
        if (item == null) {
            item = ItemStack.fromTag(FabricTagFactory.parseObject(tag));
        }
        return item;
    }
}
