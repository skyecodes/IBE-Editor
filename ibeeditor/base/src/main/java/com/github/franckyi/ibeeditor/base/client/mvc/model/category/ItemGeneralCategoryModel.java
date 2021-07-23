package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.BooleanEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.StringEntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemGeneralCategoryModel extends ItemCategoryModel {
    public ItemGeneralCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.general", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                new StringEntryModel(this, translated("ibeeditor.gui.item_id"), getBaseData().getString("id"), value -> getNewData().putString("id", value)),
                new IntegerEntryModel(this, translated("ibeeditor.gui.count"), getBaseData().getInt("Count"), value -> getNewData().putInt("Count", value)),
                new IntegerEntryModel(this, translated("ibeeditor.gui.damage"), getBaseTag().getInt("Damage"), this::setDamage),
                new BooleanEntryModel(this, translated("ibeeditor.gui.unbreakable"), getBaseTag().getBoolean("Unbreakable"), this::setUnbreakable)
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
