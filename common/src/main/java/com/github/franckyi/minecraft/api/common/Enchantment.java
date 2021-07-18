package com.github.franckyi.minecraft.api.common;

import com.github.franckyi.minecraft.api.common.world.Item;

public interface Enchantment {
    String getId();

    boolean isCurse();

    boolean canApply(Item item);

    String getName();
}
