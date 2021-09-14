package com.github.franckyi.gameadapter.api.common.item;

import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;

public interface IEnchantment extends IRegistryValue {
    boolean isCurse();

    boolean canApply(IItemStack itemStack);
}
