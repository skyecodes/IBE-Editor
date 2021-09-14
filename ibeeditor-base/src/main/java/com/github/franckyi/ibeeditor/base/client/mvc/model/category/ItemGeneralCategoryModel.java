package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.BooleanEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ItemSelectionEntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

public class ItemGeneralCategoryModel extends ItemCategoryModel {
    public ItemGeneralCategoryModel(ItemEditorModel editor) {
        super(ModTexts.GENERAL, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                new ItemSelectionEntryModel(this, ModTexts.ITEM_ID, getBaseData().getString("id"), value -> getNewData().putString("id", value)),
                new IntegerEntryModel(this, ModTexts.COUNT, getBaseData().getInt("Count"), value -> getNewData().putInt("Count", value)),
                new IntegerEntryModel(this, ModTexts.DAMAGE, getBaseTag().getInt("Damage"), this::setDamage),
                new BooleanEntryModel(this, ModTexts.UNBREAKABLE, getBaseTag().getBoolean("Unbreakable"), this::setUnbreakable)
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
