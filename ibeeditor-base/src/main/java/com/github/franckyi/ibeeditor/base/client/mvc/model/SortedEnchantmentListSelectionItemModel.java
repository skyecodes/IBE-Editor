package com.github.franckyi.ibeeditor.base.client.mvc.model;

public class SortedEnchantmentListSelectionItemModel extends EnchantmentListSelectionItemModel {
    private final boolean curse;
    private final boolean canApply;

    public SortedEnchantmentListSelectionItemModel(EnchantmentListSelectionItemModel item, boolean curse, boolean canApply) {
        super(item.getName(), item.getId(), item.getEnchantment(), item.getItem());
        this.curse = curse;
        this.canApply = canApply;
    }

    public boolean isCurse() {
        return curse;
    }

    public boolean canApply() {
        return canApply;
    }

    @Override
    public Type getType() {
        return Type.ENCHANTMENT;
    }
}
