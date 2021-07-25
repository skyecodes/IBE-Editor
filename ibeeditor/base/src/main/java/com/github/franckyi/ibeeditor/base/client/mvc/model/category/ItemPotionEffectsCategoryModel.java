package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;

public class ItemPotionEffectsCategoryModel extends ItemCategoryModel {
    public ItemPotionEffectsCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.potion_effects", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new PotionSelectionEntryModel(this));
    }

    @Override
    public int getEntryListStart() {
        return 2;
    }

    @Override
    public EntryModel createListEntry() {
        return createPotionEffectEntry(null);
    }

    private EntryModel createPotionEffectEntry(Object o) {
        return null;
    }
}
