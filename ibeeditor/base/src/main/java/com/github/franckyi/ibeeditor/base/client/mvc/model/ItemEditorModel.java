package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.*;

import java.util.function.Consumer;

public class ItemEditorModel extends StandardEditorModel<Item, ItemCategoryModel> {
    public ItemEditorModel(Item item, Consumer<Item> action, Text disabledTooltip) {
        super(item, action, disabledTooltip, "ibeeditor.text.item");
    }

    @Override
    protected void setupCategories() {
        getCategories().addAll(
                new ItemGeneralCategoryModel(this),
                new ItemDisplayCategoryModel(this),
                new ItemEnchantmentsCategoryModel(this),
                new ItemAttributeModifiersCategoryModel(this),
                new ItemHideFlagsCategoryModel(this)
        );
    }

    @Override
    public Item applyChanges() {
        CompoundTag nbt = getTarget().getData().copy();
        getCategories().forEach(categoryModel -> categoryModel.apply(nbt));
        if (nbt.contains("tag", Tag.COMPOUND_ID) && nbt.getCompound("tag").isEmpty()) {
            nbt.remove("tag");
        }
        return Game.getCommon().createItem(nbt);
    }
}
