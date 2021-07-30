package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.TextHandler;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.ListTag;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.gameadapter.api.common.text.PlainText;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.TextEntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemDisplayCategoryModel extends ItemCategoryModel {
    private ListTag newLore;

    public ItemDisplayCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.display", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new TextEntryModel(this, translated("ibeeditor.gui.custom_name"), getItemName(), this::setItemName));
        ListTag loreList = getBaseDisplay().getList("Lore", Tag.STRING_ID);
        for (int i = 0; i < loreList.size(); i++) {
            getEntries().add(createLoreEntry((PlainText) TextHandler.getSerializer().fromJson(loreList.getString(i), Text.class)));
        }
    }

    @Override
    public int getEntryListStart() {
        return 1;
    }

    @Override
    public Text getAddListEntryButtonTooltip() {
        return translated("ibeeditor.gui.lore_add");
    }

    @Override
    public EntryModel createListEntry() {
        return createLoreEntry(null);
    }

    private EntryModel createLoreEntry(PlainText value) {
        TextEntryModel entry = new TextEntryModel(this, null, value, this::addLore);
        entry.listIndexProperty().addListener(index -> entry.setLabel(translated("ibeeditor.gui.lore").with(text(Integer.toString(index + 1)))));
        return entry;
    }

    @Override
    public void apply(CompoundTag nbt) {
        newLore = ListTag.create();
        super.apply(nbt);
        getNewDisplay().putTag("Lore", newLore);
        if (getNewDisplay().getList("Lore", Tag.STRING_ID).isEmpty()) {
            getNewDisplay().remove("Lore");
        }
        if (getNewDisplay().isEmpty()) {
            getNewTag().remove("display");
        }
    }

    private PlainText getItemName() {
        String s = getBaseDisplay().getString("Name");
        return s.isEmpty() ? null : (PlainText) TextHandler.getSerializer().fromJson(s, Text.class);
    }

    private void setItemName(PlainText value) {
        if (!value.getRawText().isEmpty()) {
            if (value.getExtra() != null && !value.getExtra().isEmpty()) {
                value.getExtra().get(0).setItalic(false);
            }
            getNewDisplay().putString("Name", TextHandler.getSerializer().toJson(value));
        } else if (getNewTag().contains("display", Tag.COMPOUND_ID)) {
            getNewDisplay().remove("Name");
        }
    }

    private void addLore(PlainText value) {
        if (!value.getRawText().isEmpty()) {
            if (value.getExtra() != null && !value.getExtra().isEmpty()) {
                Text firstText = value.getExtra().get(0);
                firstText.setItalic(false);
                firstText.setColor(Color.WHITE);
            }
        }
        newLore.addString(TextHandler.getSerializer().toJson(value));
    }

    private CompoundTag getBaseDisplay() {
        return getBaseTag().getCompound("display");
    }

    private CompoundTag getNewDisplay() {
        return getNewTag().getOrCreateCompound("display");
    }
}
