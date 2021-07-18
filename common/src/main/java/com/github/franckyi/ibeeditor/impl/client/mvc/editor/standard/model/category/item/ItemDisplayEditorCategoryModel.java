package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item;

import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.ItemEditorModel;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.tag.ListTag;
import com.github.franckyi.minecraft.api.common.tag.Tag;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ItemDisplayEditorCategoryModel extends AbstractItemEditorCategoryModel {
    private ListTag newLore;

    public ItemDisplayEditorCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.editor.category.display", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new TextEditorEntryModel(this, translated("ibeeditor.gui.editor.item.entry.custom_name"), getItemName(), this::setItemName));
        ListTag loreList = getBaseDisplay().getList("Lore", Tag.STRING_ID);
        for (int i = 0; i < loreList.size(); i++) {
            getEntries().add(createLoreEntry((PlainText) Text.Serializer.GSON.fromJson(loreList.getString(i), Text.class)));
        }
    }

    @Override
    public int getEntryListStart() {
        return 1;
    }

    @Override
    public Text getAddListEntryButtonTooltip() {
        return translated("ibeeditor.gui.editor.item.entry.lore_add");
    }

    @Override
    public EditorEntryModel createListEntry() {
        return createLoreEntry(null);
    }

    private EditorEntryModel createLoreEntry(PlainText value) {
        TextEditorEntryModel entry = new TextEditorEntryModel(this, null, value, this::addLore);
        entry.listIndexProperty().addListener(index -> entry.setLabel(translated("ibeeditor.gui.editor.item.entry.lore").with(text(Integer.toString(index)))));
        return entry;
    }

    @Override
    public void apply(CompoundTag nbt) {
        newLore = Minecraft.getCommon().getTagFactory().createListTag();
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
        return s.isEmpty() ? null : (PlainText) Text.Serializer.GSON.fromJson(s, Text.class);
    }

    private void setItemName(PlainText value) {
        if (!value.getRawText().isEmpty()) {
            if (value.getExtra() != null && !value.getExtra().isEmpty()) {
                Text firstText = value.getExtra().get(0);
                if (firstText.isItalic()) {
                    value.getExtra().add(0, text("").italic(false));
                } else {
                    firstText.setItalic(false);
                }
            }
            getNewDisplay().putString("Name", Text.Serializer.GSON.toJson(value));
        } else if (getNewTag().contains("display", Tag.COMPOUND_ID)) {
            getNewDisplay().remove("Name");
        }
    }

    private void addLore(PlainText value) {
        if (!value.getRawText().isEmpty()) {
            if (value.getExtra() != null && !value.getExtra().isEmpty()) {
                Text firstText = value.getExtra().get(0);
                if (firstText.isItalic() || Color.DARK_PURPLE.equals(firstText.getColor())) {
                    value.getExtra().add(0, text("").italic(false).color(Color.WHITE));
                } else {
                    firstText.setItalic(false);
                    firstText.setColor(Color.WHITE);
                }
            }
        }
        newLore.addString(Text.Serializer.GSON.toJson(value));
    }

    private CompoundTag getBaseDisplay() {
        return getBaseTag().getCompound("display");
    }

    private CompoundTag getNewDisplay() {
        return getNewTag().getOrCreateCompound("display");
    }
}
