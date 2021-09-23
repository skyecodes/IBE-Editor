package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.translated;

public class EnchantmentEntryModel extends IntegerEntryModel {
    public EnchantmentEntryModel(CategoryModel category, Enchantment enchantment, boolean canApply, int value, Consumer<Integer> action) {
        super(category, translated(enchantment.getDescriptionId()), value, action);
        if (enchantment.isCurse()) {
            getLabel().withStyle(ChatFormatting.RED);
        } else if (canApply) {
            getLabel().withStyle(ChatFormatting.GREEN);
        }
        withWeight(2);
    }

    @Override
    public Type getType() {
        return Type.ENCHANTMENT;
    }
}
