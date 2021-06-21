package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item;

import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.IntegerEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.StringEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Item;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ItemGeneralEditorCategoryModel extends AbstractItemEditorCategoryModel {
    private CompoundTag tag;

    public ItemGeneralEditorCategoryModel(StandardEditorModel editor, Item item) {
        super("General", editor);
        getEntries().addAll(
                new StringEditorEntryModel(this, text("Item ID"), item.getTag().getString("id"), value -> getTag().putString("id", value)),
                new IntegerEditorEntryModel(this, text("Count"), item.getTag().getInt("Count"), value -> getTag().putInt("Count", value)),
                new TextEditorEntryModel(this, text("Name"), item.getName(), this::setItemName, item.getDefaultName())
        );
    }

    private void setItemName(Text value) {
        
    }

    @Override
    public void apply(CompoundTag tag) {
        this.tag = tag;
        getEntries().forEach(EditorEntryModel::apply);
    }

    public CompoundTag getTag() {
        return tag;
    }
}
