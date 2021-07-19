package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item.AbstractItemEditorCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item.ItemDisplayEditorCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item.ItemEnchantmentsEditorCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item.ItemGeneralEditorCategoryModel;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.tag.Tag;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Item;

import java.util.function.Consumer;

public class ItemEditorModel extends AbstractStandardEditorModel<Item, AbstractItemEditorCategoryModel> {
    public ItemEditorModel(Item item, Consumer<Item> action, Text disabledTooltip) {
        super(item, action, disabledTooltip, "ibeeditor.text.item");
    }

    @Override
    protected void setupCategories() {
        getCategories().addAll(
                new ItemGeneralEditorCategoryModel(this),
                new ItemDisplayEditorCategoryModel(this),
                new ItemEnchantmentsEditorCategoryModel(this)
        );
    }

    @Override
    public Item applyChanges() {
        CompoundTag nbt = getTarget().getData().copy();
        getCategories().forEach(categoryModel -> categoryModel.apply(nbt));
        if (nbt.contains("tag", Tag.COMPOUND_ID) && nbt.getCompound("tag").isEmpty()) {
            nbt.remove("tag");
        }
        return Minecraft.getCommon().createItem(nbt);
    }
}
