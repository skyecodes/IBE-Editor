package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.BooleanEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.ItemSelectionEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class ItemGeneralCategoryModel extends ItemEditorCategoryModel {
    public ItemGeneralCategoryModel(ItemEditorModel editor) {
        super(ModTexts.GENERAL, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                new ItemSelectionEntryModel(this, ModTexts.ITEM_ID, getData().getString("id"), value -> getContext().getTag().putString("id", value)),
                new IntegerEntryModel(this, ModTexts.COUNT, getData().getInt("Count"), value -> getContext().getTag().putInt("Count", value)),
                new IntegerEntryModel(this, ModTexts.DAMAGE, getData().getInt("Damage"), this::setDamage),
                new BooleanEntryModel(this, ModTexts.UNBREAKABLE, getData().getBoolean("Unbreakable"), this::setUnbreakable)
        );
    }

    private void setDamage(int value) {
        if (value == 0) {
            getData().remove("Damage");
        } else {
            getData().putInt("Damage", value);
        }
    }

    private void setUnbreakable(boolean value) {
        if (value) {
            getData().putBoolean("Unbreakable", true);
        } else {
            getData().remove("Unbreakable");
        }
    }
}
