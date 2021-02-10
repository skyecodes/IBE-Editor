package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import net.minecraft.item.ItemStack;

public class FabricItem implements Item {
    private final ItemStack item;

    public FabricItem(ItemStack item) {
        this.item = item;
    }
}
