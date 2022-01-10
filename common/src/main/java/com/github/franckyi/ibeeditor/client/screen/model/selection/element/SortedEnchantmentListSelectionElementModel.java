package com.github.franckyi.ibeeditor.client.screen.model.selection.element;

public class SortedEnchantmentListSelectionElementModel extends EnchantmentListSelectionElementModel {
    private final boolean curse;
    private final boolean canApply;

    public SortedEnchantmentListSelectionElementModel(EnchantmentListSelectionElementModel item, boolean curse, boolean canApply) {
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
