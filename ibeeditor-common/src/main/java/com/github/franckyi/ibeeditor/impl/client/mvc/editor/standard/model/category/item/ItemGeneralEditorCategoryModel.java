package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.BooleanEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.IntegerEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.StringEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.ItemEditorModel;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ItemGeneralEditorCategoryModel extends AbstractItemEditorCategoryModel {
    public ItemGeneralEditorCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.editor.category.general", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                new StringEditorEntryModel(this, translated("ibeeditor.gui.editor.item.entry.item_id"), getBaseData().getString("id"), value -> getNewData().putString("id", value)),
                new IntegerEditorEntryModel(this, translated("ibeeditor.gui.editor.item.entry.count"), getBaseData().getInt("Count"), value -> getNewData().putInt("Count", value)),
                new IntegerEditorEntryModel(this, translated("ibeeditor.gui.editor.item.entry.damage"), getBaseTag().getInt("Damage"), this::setDamage),
                new BooleanEditorEntryModel(this, translated("ibeeditor.gui.editor.item.entry.unbreakable"), getBaseTag().getBoolean("Unbreakable"), this::setUnbreakable)
        );
    }

    private void setDamage(int value) {
        if (value == 0) {
            getNewTag().remove("Damage");
        } else {
            getNewTag().putInt("Damage", value);
        }
    }

    private void setUnbreakable(boolean value) {
        if (value) {
            getNewTag().putBoolean("Unbreakable", true);
        } else {
            getNewTag().remove("Unbreakable");
        }
    }
}
