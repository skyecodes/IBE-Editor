package com.github.franckyi.ibeeditor.client.editor.gui.standard.category.item;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.EntryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.category.ItemCategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.item.AttributeModifierEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.TagHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.MutableComponent;

import java.util.UUID;
import java.util.stream.Collectors;

public class ItemAttributeModifiersCategoryModel extends ItemCategoryModel {
    private ListTag newAttributeModifiers;

    public ItemAttributeModifiersCategoryModel(ItemEditorModel editor) {
        super(ModTexts.ATTRIBUTE_MODIFIERS, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(getBaseTag().getList("AttributeModifiers", TagHelper.COMPOUND_ID)
                .stream()
                .map(CompoundTag.class::cast)
                .map(this::createModifierEntry)
                .collect(Collectors.toList()));
    }

    @Override
    public int getEntryListStart() {
        return 0;
    }

    @Override
    public int getEntryHeight() {
        return 50;
    }

    @Override
    public EntryModel createListEntry() {
        return createModifierEntry(null);
    }

    private EntryModel createModifierEntry(CompoundTag tag) {
        if (tag != null) {
            String attributeName = tag.getString("AttributeName");
            String slot = tag.getString("Slot");
            int operation = tag.getInt("Operation");
            double amount = tag.getDouble("Amount");
            UUID uuid = tag.getUUID("UUID");
            return new AttributeModifierEntryModel(this, attributeName, slot, operation, amount, uuid, this::addAttributeModifier);
        }
        return new AttributeModifierEntryModel(this, this::addAttributeModifier);
    }

    @Override
    protected MutableComponent getAddListEntryButtonTooltip() {
        return ModTexts.MODIFIER;
    }

    @Override
    public void apply(CompoundTag nbt) {
        newAttributeModifiers = new ListTag();
        super.apply(nbt);
        if (!newAttributeModifiers.isEmpty()) {
            getNewTag().put("AttributeModifiers", newAttributeModifiers);
        } else {
            getNewTag().remove("AttributeModifiers");
        }
    }

    private void addAttributeModifier(String attributeName, String slot, int operation, double amount, UUID uuid) {
        CompoundTag tag = new CompoundTag();
        tag.putString("AttributeName", attributeName);
        if (!"all".equals(slot)) {
            tag.putString("Slot", slot);
        }
        tag.putInt("Operation", operation);
        tag.putDouble("Amount", amount);
        tag.putUUID("UUID", uuid);
        newAttributeModifiers.add(tag);
    }
}
