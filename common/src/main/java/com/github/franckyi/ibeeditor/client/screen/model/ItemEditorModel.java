package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.client.screen.model.category.item.*;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;

import java.util.function.Consumer;

public class ItemEditorModel extends StandardEditorModel<ItemStack, ItemCategoryModel> {
    public ItemEditorModel(ItemStack itemStack, Consumer<ItemStack> action, Component disabledTooltip) {
        super(itemStack, action, disabledTooltip, ModTexts.ITEM, true);
    }

    @Override
    protected void setupCategories() {
        getCategories().addAll(
                new ItemGeneralCategoryModel(this),
                new ItemDisplayCategoryModel(this),
                new ItemEnchantmentsCategoryModel(this),
                new ItemAttributeModifiersCategoryModel(this),
                new ItemHideFlagsCategoryModel(this),
                new ItemBlockListCategoryModel(ModTexts.CAN_DESTROY, this, "CanDestroy")
        );
        if (getTarget().getItem() instanceof PotionItem) {
            getCategories().add(new ItemPotionEffectsCategoryModel(this));
        }
        if (getTarget().getItem() instanceof DyeableLeatherItem) {
            getCategories().add(new ItemDyeableCategoryModel(this));
        }
        if (getTarget().getItem() instanceof BlockItem) {
            getCategories().add(new ItemBlockListCategoryModel(ModTexts.CAN_PLACE_ON, this, "CanPlaceOn"));
        }
    }

    @Override
    public ItemStack applyChanges() {
        CompoundTag nbt = getTarget().save(new CompoundTag());
        getCategories().forEach(categoryModel -> categoryModel.apply(nbt));
        if (nbt.contains("tag", Tag.TAG_COMPOUND) && nbt.getCompound("tag").isEmpty()) {
            nbt.remove("tag");
        }
        return ItemStack.of(nbt);
    }

    @Override
    protected boolean saveToVault(ItemStack item) {
        return Vault.getInstance().saveItem(item.save(new CompoundTag()));
    }
}
