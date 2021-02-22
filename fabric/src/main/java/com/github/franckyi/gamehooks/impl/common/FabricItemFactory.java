package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.ItemFactory;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.item.ItemStack;

public final class FabricItemFactory implements ItemFactory {
    public static final ItemFactory INSTANCE = new FabricItemFactory();

    private FabricItemFactory() {
    }

    @Override
    public Item fromTag(ObjectTag tag) {
        return new FabricItem(ItemStack.fromTag(FabricTagFactory.INSTANCE.parseObject(tag)));
    }
}
