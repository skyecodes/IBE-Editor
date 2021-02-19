package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class FabricItem implements Item {
    private final ItemStack item;
    private ObjectTag tag;

    public FabricItem(ItemStack item) {
        this.item = item;
    }

    @Override
    public ObjectTag getTag() {
        if (tag == null) {
            tag = FabricTagFactory.INSTANCE.parseCompound(item.toTag(new CompoundTag()));
        }
        return tag;
    }
}
