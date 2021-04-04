package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.item;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.StringEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.TextEntryModel;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.api.common.text.Text;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ItemGeneralCategoryModel extends AbstractItemCategoryModel {
    private CompoundTag tag;

    public ItemGeneralCategoryModel(EditorModel editor, Item item) {
        super("General", editor);
        getEntries().addAll(
                new StringEntryModel(this, text("Item ID"), item.getTag().getString("id"), value -> getTag().putString("id", value)),
                new IntegerEntryModel(this, text("Count"), item.getTag().getInt("Count"), value -> getTag().putInt("Count", value)),
                new TextEntryModel(this, text("Name"), item.getName(), this::setItemName)
        );
    }

    private void setItemName(Text value) {
        
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
