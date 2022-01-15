package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.EnchantmentEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.MutableComponent;

public class ItemEnchantmentsCategoryModel extends ItemEditorCategoryModel {
    private ListTag newEnch;

    public ItemEnchantmentsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.ENCHANTMENTS, editor);
    }

    @Override
    protected void setupEntries() {
        getTag().getList("Enchantments", Tag.TAG_COMPOUND).stream()
                .map(CompoundTag.class::cast)
                .map(this::createEnchantment)
                .forEach(getEntries()::add);
    }

    @Override
    public int getEntryListStart() {
        return 0;
    }

    @Override
    protected MutableComponent getAddListEntryButtonTooltip() {
        return ModTexts.ENCHANTMENTS;
    }

    @Override
    public EntryModel createNewListEntry() {
        return createEnchantment("", 0);
    }

    private EnchantmentEntryModel createEnchantment(CompoundTag tag) {
        return createEnchantment(tag.getString("id"), tag.getInt("lvl"));
    }

    private EnchantmentEntryModel createEnchantment(String id, int level) {
        return new EnchantmentEntryModel(this, id, level, this::addEnchantment);
    }

    private void addEnchantment(String id, int lvl) {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", id);
        tag.putInt("lvl", lvl);
        newEnch.add(tag);
    }

    @Override
    public void apply() {
        newEnch = new ListTag();
        super.apply();
        if (!newEnch.isEmpty()) {
            getOrCreateTag().put("Enchantments", newEnch);
        } else if (getOrCreateTag().contains("Enchantments")) {
            getOrCreateTag().remove("Enchantments");
        }
    }
}
