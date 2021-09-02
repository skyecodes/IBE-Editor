package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.common.item.IEnchantment;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GuapiHelper.translated;

public class EnchantmentEntryModel extends IntegerEntryModel {
    public EnchantmentEntryModel(CategoryModel category, IEnchantment enchantment, boolean canApply, int value, Consumer<Integer> action) {
        super(category, translated(enchantment.getName()), value, action);
        if (enchantment.isCurse()) {
            getLabel().setColor(Color.RED);
        } else if (canApply) {
            getLabel().setColor(Color.GREEN);
        }
        withWeight(2);
    }

    @Override
    public Type getType() {
        return Type.ENCHANTMENT;
    }
}
