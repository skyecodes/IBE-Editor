package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.ibeeditor.base.client.mvc.model.category.*;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import com.github.franckyi.ibeeditor.base.common.TagHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;

import java.util.function.Consumer;

public class ItemEditorModel extends StandardEditorModel<ItemStack, ItemCategoryModel> {
    public ItemEditorModel(ItemStack itemStack, Consumer<ItemStack> action, Component disabledTooltip) {
        super(itemStack, action, disabledTooltip, ModTexts.ITEM);
    }

    @Override
    protected void setupCategories() {
        getCategories().addAll(
                new ItemGeneralCategoryModel(this),
                new ItemDisplayCategoryModel(this)
        );
        if (getTarget().getItem() instanceof PotionItem) {
            getCategories().add(new ItemPotionEffectsCategoryModel(this));
        }
        if (getTarget().getItem() instanceof DyeableLeatherItem) {
            getCategories().add(new ItemDyeableCategoryModel(this));
        }
        getCategories().addAll(
                new ItemEnchantmentsCategoryModel(this),
                new ItemAttributeModifiersCategoryModel(this),
                new ItemHideFlagsCategoryModel(this),
                new ItemBlockListCategoryModel(ModTexts.CAN_DESTROY, this, "CanDestroy")
        );
        if (getTarget().getItem() instanceof BlockItem) {
            getCategories().add(new ItemBlockListCategoryModel(ModTexts.CAN_PLACE_ON, this, "CanPlaceOn"));
        }
    }

    @Override
    public ItemStack applyChanges() {
        CompoundTag nbt = getTarget().save(new CompoundTag());
        getCategories().forEach(categoryModel -> categoryModel.apply(nbt));
        if (nbt.contains("tag", TagHelper.COMPOUND_ID) && nbt.getCompound("tag").isEmpty()) {
            nbt.remove("tag");
        }
        return ItemStack.of(nbt);
    }
}
