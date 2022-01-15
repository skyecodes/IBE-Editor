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
import net.minecraft.world.item.ItemStack;

public class ItemDisplayCategoryModel extends ItemEditorCategoryModel {
    private ListTag newLore;

    public ItemDisplayCategoryModel(ItemEditorModel editor) {
        super(ModTexts.DISPLAY, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().add(new TextEntryModel(this, ModTexts.CUSTOM_NAME, getItemName(), this::setItemName));
        getDisplay().getList("Lore", Tag.TAG_STRING).stream()
                .map(Tag::getAsString)
                .map(Component.Serializer::fromJson)
                .map(TextComponent.class::cast)
                .map(this::createLoreEntry)
                .forEach(getEntries()::add);
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
    public EntryModel createNewListEntry() {
        return createLoreEntry(null);
    }

    private EntryModel createLoreEntry(TextComponent value) {
        TextEntryModel entry = new TextEntryModel(this, null, value, this::addLore);
        entry.listIndexProperty().addListener(index -> entry.setLabel(ModTexts.lore(index + 1)));
        return entry;
    }

    @Override
    public void apply() {
        newLore = new ListTag();
        super.apply();
        if (!newLore.isEmpty()) {
            getOrCreateDisplay().put("Lore", newLore);
        } else if (getOrCreateTag().contains("Lore")) {
            getOrCreateDisplay().remove("Lore");
        }
        if (getDisplay().isEmpty()) {
            getTag().remove(ItemStack.TAG_DISPLAY);
        }
    }

    private TextComponent getItemName() {
        String s = getDisplay().getString(ItemStack.TAG_DISPLAY_NAME);
        return s.isEmpty() ? null : (TextComponent) Component.Serializer.fromJson(s);
    }

    private void setItemName(TextComponent value) {
        if (!value.getString().isEmpty()) {
            if (value.getText().isEmpty() && !value.getSiblings().isEmpty()) {
                value.withStyle(style -> style.withItalic(false));
            }
            getOrCreateDisplay().putString(ItemStack.TAG_DISPLAY_NAME, Component.Serializer.toJson(value));
        } else {
            getOrCreateDisplay().remove(ItemStack.TAG_DISPLAY_NAME);
        }
    }

    private void addLore(TextComponent value) {
        if (!value.getString().isEmpty() && value.getText().isEmpty() && !value.getSiblings().isEmpty()) {
            value.withStyle(style -> style.withItalic(false).withColor(ChatFormatting.WHITE));
        }
        newLore.add(StringTag.valueOf(Component.Serializer.toJson(value)));
    }

    private CompoundTag getDisplay() {
        return getSubTag(ItemStack.TAG_DISPLAY);
    }

    private CompoundTag getOrCreateDisplay() {
        return getOrCreateSubTag(ItemStack.TAG_DISPLAY);
    }
}
