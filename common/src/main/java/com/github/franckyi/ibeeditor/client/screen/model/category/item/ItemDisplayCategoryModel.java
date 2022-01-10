package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.TextEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;

public class ItemDisplayCategoryModel extends ItemCategoryModel {
    private ListTag newLore;

    public ItemDisplayCategoryModel(ItemEditorModel editor) {
        super(ModTexts.DISPLAY, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new TextEntryModel(this, ModTexts.CUSTOM_NAME, getItemName(), this::setItemName));
        ListTag loreList = getBaseDisplay().getList("Lore", Tag.TAG_STRING);
        for (int i = 0; i < loreList.size(); i++) {
            getEntries().add(createLoreEntry((TextComponent) Component.Serializer.fromJson(loreList.getString(i))));
        }
    }

    @Override
    public int getEntryListStart() {
        return 1;
    }

    @Override
    public MutableComponent getAddListEntryButtonTooltip() {
        return ModTexts.LORE_ADD;
    }

    @Override
    public EntryModel createListEntry() {
        return createLoreEntry(null);
    }

    private EntryModel createLoreEntry(TextComponent value) {
        TextEntryModel entry = new TextEntryModel(this, null, value, this::addLore);
        entry.listIndexProperty().addListener(index -> entry.setLabel(ModTexts.lore(index + 1)));
        return entry;
    }

    @Override
    public void apply(CompoundTag nbt) {
        newLore = new ListTag();
        super.apply(nbt);
        getNewDisplay().put("Lore", newLore);
        if (getNewDisplay().getList("Lore", Tag.TAG_STRING).isEmpty()) {
            getNewDisplay().remove("Lore");
        }
        if (getNewDisplay().isEmpty()) {
            getNewTag().remove("display");
        }
    }

    private TextComponent getItemName() {
        String s = getBaseDisplay().getString("Name");
        return s.isEmpty() ? null : (TextComponent) Component.Serializer.fromJson(s);
    }

    private void setItemName(TextComponent value) {
        if (!value.getString().isEmpty()) {
            if (value.getText().isEmpty() && !value.getSiblings().isEmpty()) {
                value.withStyle(style -> style.withItalic(false));
            }
            getNewDisplay().putString("Name", Component.Serializer.toJson(value));
        } else if (getNewTag().contains("display", Tag.TAG_COMPOUND)) {
            getNewDisplay().remove("Name");
        }
    }

    private void addLore(TextComponent value) {
        if (!value.getString().isEmpty() && value.getText().isEmpty() && !value.getSiblings().isEmpty()) {
            value.withStyle(style -> style.withItalic(false).withColor(ChatFormatting.WHITE));
        }
        newLore.add(StringTag.valueOf(Component.Serializer.toJson(value)));
    }

    private CompoundTag getBaseDisplay() {
        return getBaseTag().getCompound("display");
    }

    private CompoundTag getNewDisplay() {
        if (getNewTag().contains("display")) {
            return getNewTag().getCompound("display");
        }
        CompoundTag tag = new CompoundTag();
        getNewTag().put("display", tag);
        return tag;
    }
}
