package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.AttributeModifierEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemAttributeModifiersCategoryModel extends ItemCategoryModel {
    private IListTag newAttributeModifiers;

    public ItemAttributeModifiersCategoryModel(ItemEditorModel editor) {
        super("ibeeditor.gui.attribute_modifiers", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(getBaseTag().getList("AttributeModifiers", ITag.COMPOUND_ID)
                .stream()
                .map(ICompoundTag.class::cast)
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

    private EntryModel createModifierEntry(ICompoundTag tag) {
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
    protected Text getAddListEntryButtonTooltip() {
        return translated("ibeeditor.gui.modifier");
    }

    @Override
    public void apply(ICompoundTag nbt) {
        newAttributeModifiers = IListTag.create();
        super.apply(nbt);
        if (!newAttributeModifiers.isEmpty()) {
            getNewTag().putTag("AttributeModifiers", newAttributeModifiers);
        } else {
            getNewTag().remove("AttributeModifiers");
        }
    }

    private void addAttributeModifier(String attributeName, String slot, int operation, double amount, UUID uuid) {
        ICompoundTag tag = ICompoundTag.create();
        tag.putString("AttributeName", attributeName);
        tag.putString("Slot", slot);
        tag.putInt("Operation", operation);
        tag.putDouble("Amount", amount);
        tag.putUUID("UUID", uuid);
        newAttributeModifiers.add(tag);
    }
}
