package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item.AbstractItemEditorCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item.ItemGeneralEditorCategoryModel;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Item;

import java.util.function.Consumer;

public class ItemEditorModel extends AbstractStandardEditorModel<Item, AbstractItemEditorCategoryModel> {
    public ItemEditorModel(Item item, Consumer<Item> action, Text disabledTooltip) {
        super(item, action, disabledTooltip, "ibeeditor.gui.item_editor");
        getCategories().addAll(
                new ItemGeneralEditorCategoryModel(this, item)
        );
    }

    @Override
    public Item applyChanges() {
        CompoundTag tag = getTarget().getTag().copy();
        getCategories().forEach(categoryModel -> categoryModel.apply(tag));
        return Minecraft.getCommon().createItem(tag);
    }
}
