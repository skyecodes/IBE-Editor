package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.context.ItemEditorContext;
import com.github.franckyi.ibeeditor.client.screen.model.category.item.*;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;

public class ItemEditorModel extends StandardEditorModel {
    public ItemEditorModel(ItemEditorContext context) {
        super(context);
    }

    @Override
    public ItemEditorContext getContext() {
        return (ItemEditorContext) super.getContext();
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
        Item item = getContext().getItemStack().getItem();
        if (item instanceof PotionItem) {
            getCategories().add(new ItemPotionEffectsCategoryModel(this));
        }
        if (item instanceof DyeableLeatherItem) {
            getCategories().add(new ItemDyeableCategoryModel(this));
        }
        if (item instanceof BlockItem) {
            getCategories().add(new ItemBlockListCategoryModel(ModTexts.CAN_PLACE_ON, this, "CanPlaceOn"));
        }
        /*if (item instanceof SpawnEggItem) {
            getCategories().add(new ItemSpawnEggCategoryModel(this, (SpawnEggItem) item));
        }*/
    }

    @Override
    public void apply() {
        super.apply();
        var tag = getContext().getTag();
        if (tag.contains("tag", Tag.TAG_COMPOUND) && tag.getCompound("tag").isEmpty()) {
            tag.remove("tag");
        }
    }

}
