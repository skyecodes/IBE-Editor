package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.item;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.AbstractItemCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.IntegerEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.StringEntryModel;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.Item;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ItemGeneralCategoryModel extends AbstractItemCategoryModel {
    private CompoundTag tag;

    public ItemGeneralCategoryModel(EditorModel editor, Item item) {
        super("General", editor);
        getEntries().addAll(
                new StringEntryModel(text("Item ID"), item.getTag().getString("id"), value -> getTag().putString("id", value)),
                new IntegerEntryModel(text("Count"), item.getTag().getInt("Count"), value -> getTag().putInt("Count", value))
        );
    }

    @Override
    public void apply(CompoundTag tag) {
        this.tag = tag;
        getEntries().forEach(EntryModel::apply);
    }

    public CompoundTag getTag() {
        return tag;
    }
}
