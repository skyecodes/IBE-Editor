package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ForgeItem implements Item {
    private ItemStack item;
    private ObjectTag tag;

    public ForgeItem(ItemStack item) {
        this.item = item;
    }

    public ForgeItem(ObjectTag tag) {
        this.tag = tag;
    }

    @Override
    public ObjectTag getTag() {
        if (tag == null) {
            tag = ForgeTagFactory.parseCompound(item.write(new CompoundNBT()));
        }
        return tag;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ItemStack get() {
        if (item == null) {
            item = ItemStack.read(ForgeTagFactory.parseObject(tag));
        }
        return item;
    }
}
