package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.item.ItemGeneralCategoryModel;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Item;

import java.util.function.Consumer;

public class ItemEditorModel extends AbstractEditorModel<Item, AbstractItemCategoryModel> {
    public ItemEditorModel(Item item, Consumer<Item> action, Text disabledTooltip) {
        super(item, action, disabledTooltip);
        getCategories().addAll(
                new ItemGeneralCategoryModel(this, item)
        );
    }

    @Override
    public Item applyChanges() {
        CompoundTag tag = getTarget().getTag().copy();
        getCategories().forEach(categoryModel -> categoryModel.apply(tag));
        return Minecraft.getCommon().createItem(tag);
    }
}
