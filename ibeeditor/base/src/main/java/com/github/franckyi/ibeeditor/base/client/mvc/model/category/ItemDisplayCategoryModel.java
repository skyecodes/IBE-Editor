package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.TextEntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemDisplayCategoryModel extends ItemCategoryModel {
    private IListTag newLore;

    public ItemDisplayCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.display", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new TextEntryModel(this, translated("ibeeditor.gui.custom_name"), getItemName(), this::setItemName));
        IListTag loreList = getBaseDisplay().getList("Lore", ITag.STRING_ID);
        for (int i = 0; i < loreList.size(); i++) {
            getEntries().add(createLoreEntry(IText.fromJson(loreList.getString(i))));
        }
    }

    @Override
    public int getEntryListStart() {
        return 1;
    }

    @Override
    public IText getAddListEntryButtonTooltip() {
        return translated("ibeeditor.gui.lore_add");
    }

    @Override
    public EntryModel createListEntry() {
        return createLoreEntry(null);
    }

    private EntryModel createLoreEntry(IPlainText value) {
        TextEntryModel entry = new TextEntryModel(this, null, value, this::addLore);
        entry.listIndexProperty().addListener(index -> entry.setLabel(translated("ibeeditor.gui.lore", text(Integer.toString(index + 1)))));
        return entry;
    }

    @Override
    public void apply(ICompoundTag nbt) {
        newLore = IListTag.create();
        super.apply(nbt);
        getNewDisplay().putTag("Lore", newLore);
        if (getNewDisplay().getList("Lore", ITag.STRING_ID).isEmpty()) {
            getNewDisplay().remove("Lore");
        }
        if (getNewDisplay().isEmpty()) {
            getNewTag().remove("display");
        }
    }

    private IPlainText getItemName() {
        String s = getBaseDisplay().getString("Name");
        return s.isEmpty() ? null : IText.fromJson(s);
    }

    private void setItemName(IPlainText value) {
        if (!value.getRawText().isEmpty()) {
            if (value.getExtra() != null && !value.getExtra().isEmpty()) {
                value.getExtra().get(0).setItalic(false);
            }
            getNewDisplay().putString("Name", value.toJson());
        } else if (getNewTag().contains("display", ITag.COMPOUND_ID)) {
            getNewDisplay().remove("Name");
        }
    }

    private void addLore(IPlainText value) {
        if (!value.getRawText().isEmpty()) {
            if (value.getExtra() != null && !value.getExtra().isEmpty()) {
                IText firstText = value.getExtra().get(0);
                firstText.setItalic(false);
                firstText.setColor(Color.WHITE);
            }
        }
        newLore.addString(value.toJson());
    }

    private ICompoundTag getBaseDisplay() {
        return getBaseTag().getCompound("display");
    }

    private ICompoundTag getNewDisplay() {
        return getNewTag().getOrCreateCompound("display");
    }
}
