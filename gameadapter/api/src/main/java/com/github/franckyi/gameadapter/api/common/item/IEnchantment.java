package com.github.franckyi.gameadapter.api.common.item;

import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;

public interface IEnchantment extends IRegistryValue {
    int TARGET_ARMOR = 0;
    int TARGET_ARMOR_FEET = 1;
    int TARGET_ARMOR_LEGS = 2;
    int TARGET_ARMOR_CHEST = 3;
    int TARGET_ARMOR_HEAD = 4;
    int TARGET_WEAPON = 5;
    int TARGET_DIGGER = 6;
    int TARGET_FISHING_ROD = 7;
    int TARGET_TRIDENT = 8;
    int TARGET_BREAKABLE = 9;
    int TARGET_BOW = 10;
    int TARGET_WEARABLE = 11;
    int TARGET_CROSSBOW = 12;
    int TARGET_VANISHABLE = 13;

    boolean isCurse();

    boolean canApply(IItemStack itemStack);

    int getTarget();
}
