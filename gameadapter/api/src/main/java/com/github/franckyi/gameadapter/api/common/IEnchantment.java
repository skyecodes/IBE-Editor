package com.github.franckyi.gameadapter.api.common;

public interface IEnchantment extends IRegistryValue {
    boolean isCurse();

    boolean canApply(IItemStack itemStack);
}
