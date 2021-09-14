package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.Game;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.*;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import java.util.function.Consumer;

public class ItemEditorModel extends StandardEditorModel<IItemStack, ItemCategoryModel> {
    public ItemEditorModel(IItemStack itemStack, Consumer<IItemStack> action, IText disabledTooltip) {
        super(itemStack, action, disabledTooltip, ModTexts.ITEM);
    }

    @Override
    protected void setupCategories() {
        getCategories().addAll(
                new ItemGeneralCategoryModel(this),
                new ItemDisplayCategoryModel(this)
        );
        if (getTarget().isPotionItem()) {
            getCategories().add(new ItemPotionEffectsCategoryModel(this));
        }
        if (getTarget().isDyeableItem()) {
            getCategories().add(new ItemDyeableCategoryModel(this));
        }
        getCategories().addAll(
                new ItemEnchantmentsCategoryModel(this),
                new ItemAttributeModifiersCategoryModel(this),
                new ItemHideFlagsCategoryModel(this),
                new ItemBlockListCategoryModel(ModTexts.CAN_DESTROY, this, "CanDestroy")
        );
        if (getTarget().isBlockItem()) {
            getCategories().add(new ItemBlockListCategoryModel(ModTexts.CAN_PLACE_ON, this, "CanPlaceOn"));
        }
    }

    @Override
    public IItemStack applyChanges() {
        ICompoundTag nbt = getTarget().getData().copy();
        getCategories().forEach(categoryModel -> categoryModel.apply(nbt));
        if (nbt.contains("tag", ITag.COMPOUND_ID) && nbt.getCompound("tag").isEmpty()) {
            nbt.remove("tag");
        }
        return Game.getCommon().createItemFromTag(nbt);
    }
}
