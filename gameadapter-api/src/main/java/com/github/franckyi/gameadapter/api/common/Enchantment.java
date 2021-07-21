package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.api.common.world.Item;

public interface Enchantment {
    String getId();

    boolean isCurse();

    boolean canApply(Item item);

    String getName();
}
