package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.item.IEnchantment;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;

public class EnchantmentListSelectionItemModel extends ItemListSelectionItemModel {
    private final IEnchantment enchantment;

    public EnchantmentListSelectionItemModel(String name, IIdentifier id, IEnchantment enchantment, IItemStack item) {
        super(name, id, item);
        this.enchantment = enchantment;
    }

    public IEnchantment getEnchantment() {
        return enchantment;
    }
}
