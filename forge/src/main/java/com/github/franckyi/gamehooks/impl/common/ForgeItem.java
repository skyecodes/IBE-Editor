package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ForgeItem implements Item {
    private final ItemStack item;
    private ObjectTag tag;

    public ForgeItem(ItemStack item) {
        this.item = item;
    }

    @Override
    public ObjectTag getTag() {
        if (tag == null) {
            tag = ForgeTagFactory.INSTANCE.parseCompound(item.write(new CompoundNBT()));
        }
        return tag;
    }
}
