package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.ItemFactory;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.item.ItemStack;

public final class ForgeItemFactory implements ItemFactory {
    public static final ItemFactory INSTANCE = new ForgeItemFactory();

    private ForgeItemFactory() {
    }

    @Override
    public Item fromTag(ObjectTag tag) {
        return new ForgeItem(ItemStack.read(ForgeTagFactory.INSTANCE.parseObject(tag)));
    }
}
