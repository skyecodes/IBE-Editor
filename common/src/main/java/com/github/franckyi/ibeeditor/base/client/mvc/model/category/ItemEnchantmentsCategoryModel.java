package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnchantmentEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.MutableComponent;

public class ItemEnchantmentsCategoryModel extends ItemCategoryModel {
    private ListTag newEnch;

    public ItemEnchantmentsCategoryModel(ItemEditorModel editor) {
        super(ModTexts.ENCHANTMENTS, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(getBaseTag().getList("Enchantments", Tag.TAG_COMPOUND)
                .stream()
                .map(CompoundTag.class::cast)
                .map(this::createEnchantment)
                .toList());
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
    public EntryModel createListEntry() {
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
    public void apply(CompoundTag nbt) {
        newEnch = new ListTag();
        super.apply(nbt);
        if (!newEnch.isEmpty()) {
            getNewTag().put("Enchantments", newEnch);
        } else {
            getNewTag().remove("Enchantments");
        }
    }
}
